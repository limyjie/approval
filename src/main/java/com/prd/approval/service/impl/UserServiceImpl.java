/**
 * Author: lin
 * Date: 2019/3/5 18:31
 */
package com.prd.approval.service.impl;


import com.prd.approval.dao.*;
import com.prd.approval.entity.Event;
import com.prd.approval.entity.Message;
import com.prd.approval.entity.Process;
import com.prd.approval.entity.User;
import com.prd.approval.service.UserService;
import com.prd.approval.utils.IDNOUtil;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TemplateDAO templateDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private ProcessDAO processDAO;

    @Autowired
    private StepStaffDAO stepStaffDAO;

    @Override
    public ResponseUtil<User> login(User webUser) {

        if (webUser == null
                || webUser.getId() == null || webUser.getId().isEmpty()
                || webUser.getPassword() == null || webUser.getPassword().isEmpty()
        ) {
            return new ResponseUtil<>(0, "账号和密码不能为空");
        }
        User user = userDAO.selectUserByIdAndPassword(webUser);

        if (user == null) {
            return new ResponseUtil<>(0, "账号或密码错误");
        }

        return new ResponseUtil<>(1, "登陆成功", user);
    }

    @Override
    public ResponseUtil<List<Message>> checkMessage(String userId) {

        stepStaffDAO.whetherHaveNewEventToDo(userId);

        return null;
    }

    @Override
    public ResponseUtil<List<Message>> getMessage() {

        List<Message> messageList = messageDAO.selectAllApprovalMessage();

        return new ResponseUtil<>(1, "消息查询成功", messageList);
    }

    @Transactional
    @Override
    public ResponseUtil<Message> sendMessage(Message message) {

        message.setId(IDNOUtil.getIDNO());

        message.setSendTime(new Timestamp(System.currentTimeMillis()));
        message.setHaveAlert("0");
        message.setHaveRead("0");
        message.setHaveDone("0");
        message.setMessageType("ap");
        int result = messageDAO.insertMessage(message);
        if (result == 1) {
            return new ResponseUtil<>(1, "发送消息成功");
        }

        return new ResponseUtil<>(0, "发送消息失败");
    }

    @Override
    public ResponseUtil<Map<String, Object>> getApprovalEvent(String eventId) {

        Event event = templateDAO.selectTemplateById(eventId);
        if (event == null) {
            return new ResponseUtil<>(0, "审批事件" + eventId + "不存在");
        }

        String currentProcessId = event.getCurrentStepId();
        Process currentProcess = processDAO.selectProcessById(currentProcessId);
        if (currentProcess == null) {
            return new ResponseUtil<>(0, "审批事件" + eventId + "当前执行阶段" + currentProcessId + "异常");
        }

        Map<String, Object> map = new HashMap<>();
        map.put("event", event);
        map.put("currentProcess", currentProcess);

        return new ResponseUtil<>(1, "查询审批事件成功", map);

    }

    // result：未审批notExamine 通过pass 未通过notPass
    @Transactional
    @Override
    public ResponseUtil<Event> doApproval(String eventId, String result, String remarks) {

        Event event = templateDAO.selectTemplateById(eventId);
        String stepId = event.getCurrentStepId();
        Process current = processDAO.selectProcessById(stepId);

        String msg;
        switch (event.getStatus()){
            case "3":msg = "该审批事件已执行完毕，无需继续执行";break;
            case "4":msg = "该审批事件被拒绝，无法执行审批";break;
            case "5":msg = "该审批事件被中止，无法执行审批";break;
            default:msg = null;
        }
        if(msg!=null){
            return new ResponseUtil<>(1,msg);
        }
/*
 event: status  1 新建， 3 通过审批，  4 被拒绝， 5 被中止
 process :status 1 新建，2 正在执行(开启),3 通过，4 = 被拒绝
    逻辑：对审批事件A 的 当前阶段a 执行审批，
         如果审批结果result 为通过，则将a的剩余审批次数-1，
            如果a的剩余审批次数为0 ，则a审批结束，更新a的状态，
                寻找下一审批阶段b，
                    如果b == null ，说明审批阶段已经全部执行结束
                    否则 根据b将A的当前阶段和当前阶段的次序更新
            否则 继续执行a的审批
         如果审批结果result 为不通过，则将a的状态设置为4，A的状态设置为4
 */
        switch (result) {
            case "pass": {
                //times remain -- , status = 3
                int remain = current.getTimesRemain() - 1;
                current.setTimesRemain(remain);
                current.setStatus("2");
                //if times remain == 0 , update current process
                if (remain == 0) {
                    current.setStatus("3");
                    Process next = processDAO.selectNextProcess(current);
                    if(next == null){
                        event.setStatus("3");
                        processDAO.updateProcess(current);
                        templateDAO.updateTemplate(event);
                        return new ResponseUtil<>(1,"审批事件 "+eventId+" 全部执行结束");
                    }
                    event.setCurrentStepId(next.getId());
                    event.setCurrentStepSortNo(next.getSortNo());
                    processDAO.updateProcess(current);
                    templateDAO.updateTemplate(event);
                    return new ResponseUtil<>(1,"执行审批阶段 "+current.getId()+" 成功,审批事件进入下一审批阶段："+next.getId());
                }
                processDAO.updateProcess(current);
                templateDAO.updateTemplate(event);
                return new ResponseUtil<>(1, "执行审批阶段 "+current.getId()+" 成功，该阶段剩余审批次数："+current.getTimesRemain());
            }
            case "notPass": {
                current.setStatus("4");
                event.setStatus("4");
                processDAO.updateProcess(current);
                templateDAO.updateTemplate(event);
                return new ResponseUtil<>(1, "拒绝审批阶段成功");
            }
            default: {
                return new ResponseUtil<>(0, "审批结果不正确");
            }
        }
    }

    @Override
    public ResponseUtil<Map<String, Object>> getTargetBill(String id) {



        return null;
    }

}
 /* optionalEventId.ifPresent((value)->{
           System.out.println(optionalEventId.map(templateDAO::selectTemplateById).get().toString());
       });*/


        /*
        如果 optionalEventId 的 value 存在，则执行templateDAO的selectTemplateById 方法，
        map :
        If a value is present,
        apply the provided mapping function to it,
        and if the result is non-null,
        return an Optional describing the result.
        Otherwise return an empty Optional.
         */
// System.out.println(optionalEventId.map(templateDAO::selectTemplateById));
      /*  Optional<String> optionalEventId = Optional.ofNullable(eventId);
        Optional<Event> optionalEvent = optionalEventId.map(templateDAO::selectTemplateById);
        Optional<String> optionalCurrentStepId = optionalEvent.map(Event::getCurrentStepId);
        Optional<Process> optionalProcess = optionalCurrentStepId.map(processDAO::selectProcessById);
        optionalEventId.ifPresent(
                ()-{}
        );*/


//System.out.println(optionalEventId.map(templateDAO::selectTemplateById).map(Event::getCurrentStepId).map(processDAO::selectProcessById));
//  Event event = templateDAO.selectTemplateById(eventId);
// String current  = event.getCurrentStepId();
// Process currentProcess = processDAO.selectProcessById(current);
// Optional<Event>  optionalEvent = Optional.ofNullable(templateDAO.selectTemplateById(eventId));


// Optional<Process> optionalProcess =  optionalEvent.flatMap(processDAO::selectProcessById())

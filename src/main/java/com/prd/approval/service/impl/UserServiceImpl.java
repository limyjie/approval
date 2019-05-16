/**
 * Author: lin
 * Date: 2019/3/5 18:31
 */
package com.prd.approval.service.impl;


import com.prd.approval.dao.*;
import com.prd.approval.entity.*;
import com.prd.approval.entity.Process;
import com.prd.approval.service.UserService;
import com.prd.approval.utils.IDNOUtil;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private TemplateDAO templateDAO;

    @Autowired
    private AuditorDAO auditorDAO;

    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private ProcessDAO processDAO;

    @Autowired
    private StepStaffDAO stepStaffDAO;

    @Autowired
    private ApplyHeaderDAO applyHeaderDAO;

    @Autowired
    private EventCreatorDAO eventCreatorDAO;

    @Override
    public ResponseUtil<Map<String, String>> login(User webUser) {

        if (webUser == null
                || webUser.getId() == null || webUser.getId().isEmpty()
                || webUser.getPassword() == null || webUser.getPassword().isEmpty()
        ) {
            return new ResponseUtil<>(0, "账号和密码不能为空");
        }
        Map<String, String> map = userDAO.selectUserByIdAndPassword(webUser);

        if (map == null) {
            return new ResponseUtil<>(0, "账号或密码错误");
        }

        return new ResponseUtil<>(1, "登陆成功", map);
    }

    @Override
    public ResponseUtil<List<Event>> checkMessage(String userId) {

        List<Event> eventList = templateDAO.selectTodoEventListByUserId(userId);

        if (eventList.size() == 0) {
            return new ResponseUtil<>(1, "暂无消息");
        }
        return new ResponseUtil<>(1, "您有新的消息", eventList);

    }

    @Override
    public ResponseUtil<List<Message>> getMessage(String toUserId) {

        List<Message> messageList = messageDAO.selectAllMessageToUser(toUserId);
        if (messageList.size() == 0) {
            return new ResponseUtil<>(0, "消息为空");
        }
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
        message.setMessageType("0");
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
    public ResponseUtil<Event> doApproval(String eventId, String result, String remarks, String auditorId) {

        Event event = templateDAO.selectTemplateById(eventId);
        String msg;
        switch (event.getStatus()) {
            case "3":
                msg = "该审批事件已执行完毕，无需继续执行";
                break;
            case "4":
                msg = "该审批事件被拒绝，无法执行审批";
                break;
            case "5":
                msg = "该审批事件被中止，无法执行审批";
                break;
            default:
                msg = null;
        }
        if (msg != null) {
            return new ResponseUtil<>(1, msg);
        }


        Process current = processDAO.selectProcessById(event.getCurrentStepId());
        StepStaff stepStaff = stepStaffDAO.selectByStepIdAndStaffId(current.getId(), auditorId);

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
                //审批备注
                stepStaff.setApComment(remarks);
                //审批结果  1 = 未审批， 3 = 审批通过， 4 = 拒绝
                stepStaff.setApResult("3");
                // 1 = 未执行，2 = 已执行(通过或拒绝)
                stepStaff.setStatus("2");
                stepStaff.setApDate(new Timestamp(System.currentTimeMillis()));
                stepStaffDAO.updateStepStaffByStepIdAndStaffId(stepStaff);
                //if times remain == 0 , update current process
                if (remain == 0) {
                    current.setStatus("3");
                    Process next = processDAO.selectNextProcess(current);
                    if (next == null) {
                        event.setStatus("3");
                        processDAO.updateProcess(current);
                        templateDAO.updateTemplate(event);
                        return new ResponseUtil<>(1, "审批事件 " + eventId + " 全部执行结束");
                    }
                    event.setCurrentStepId(next.getId());
                    event.setCurrentStepSortNo(next.getSortNo());
                    processDAO.updateProcess(current);
                    templateDAO.updateTemplate(event);
                    return new ResponseUtil<>(1, "执行审批阶段 " + current.getId() + " 成功,审批事件进入下一审批阶段：" + next.getId());
                }
                processDAO.updateProcess(current);
                templateDAO.updateTemplate(event);
                return new ResponseUtil<>(1, "执行审批阶段 " + current.getId() + " 成功，该阶段剩余审批次数：" + current.getTimesRemain());
            }
            case "notPass": {
                stepStaff.setApComment(remarks);
                //1 = 未审批， 3 = 审批通过， 4 = 拒绝
                stepStaff.setApResult("4");
                // 1 = 未执行，2 = 已执行(通过或拒绝)
                stepStaff.setStatus("2");
                stepStaff.setApDate(new Timestamp(System.currentTimeMillis()));
                current.setTimesRemain(current.getTimesRemain()-1);
                int personRemainNum = stepStaffDAO.selectStepUnApprovalPersonNumber(stepStaff.getId());
                if(current.getTimesRemain()< personRemainNum){
                    current.setStatus("4");
                    event.setStatus("4");
                    // 不通过时，检查：当事件的剩余审批次数 小于 事件需要通过的次数时 ，发消息给事件发起人
                    sendMessageToEventCreatorForReject(event,auditorId);
                }

                stepStaffDAO.updateStepStaffByStepIdAndStaffId(stepStaff);
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
    public ResponseUtil<Map<String,Object>> getTargetBill(String applyNo) {

        Map<String,Object> resultMap = applyHeaderDAO.selectApplyHeaderByApplyNo(applyNo);

        if (resultMap != null) {
            return new ResponseUtil<>(1, "目标单据类型查询成功", resultMap);
        }
        return new ResponseUtil<>(0, "目标单据类型查询结果为空");

    }

    @Override
    public ResponseUtil<Map<String, Object>> getEventByIdAndUser(String eventId) {
        Map<String, Object> map = templateDAO.selectEventAndCreatorByEventId(eventId);
        if (map == null || map.isEmpty()) {
            return new ResponseUtil<>(0, "事件 " + eventId + " 不存在");
        }

        return new ResponseUtil<>(1, "查询成功", map);
    }

    @Override
    public ResponseUtil<List<Map<String, Object>>> getEventByCase(String billNo, String creator, String eventStatus) {
        billNo = billNo == null ? null : billNo.trim().isEmpty() ? null : billNo;
        creator = creator == null ? null : creator.trim().isEmpty() ? null : creator;
        eventStatus = eventStatus == null ? null : eventStatus.trim().isEmpty() ? null : eventStatus;

        List<Map<String,Object>> resultMapList = templateDAO.selectEventByCase(billNo,creator, eventStatus);

        if (resultMapList.size() == 0) {
            return new ResponseUtil<>(1, "查询成功，结果为空");
        }
        return new ResponseUtil<>(1, "查询成功", resultMapList);


        /* List<Map<String, Object>> resultMapList = new LinkedList<>();
        if (creator == null) {
            for (Event event : eventList) {
                Map<String, Object> map = new HashMap<>();
                map.put("event", event);
                List<EventCreator> eventCreatorList = eventCreatorDAO.selectEventCreatorByTemplateId(event.getId());
                map.put("creatorList", eventCreatorList);
                resultMapList.add(map);
            }
        } else {
            for (Event event : eventList) {
                List<EventCreator> eventCreatorList = eventCreatorDAO.selectEventCreatorByTemplateId(event.getId());
                for(EventCreator eventCreator){
                    if(eventCreator.getId())
                }
            }

        }*/
    }

    @Override
    public ResponseUtil<Map<String, Object>> getEventProcessCreator(Map<String, String> map) {
        String queryMethod = map.get("queryMethod");
        String queryId = map.get("queryId");
        Map<String, Object> resultMap = null;
        if ("stepStaff".equals(queryMethod)) {
            resultMap = new HashMap<>();

            StepStaff stepStaff = stepStaffDAO.selectByStepStaffId(queryId);
            Process process = processDAO.selectProcessById(stepStaff.gethId());
            Event event = templateDAO.selectTemplateById(process.getEventId());
            Message message = messageDAO.selectMessageByEventId(event.getId());

            resultMap.put("event", event);
            resultMap.put("currentStep", process);
            resultMap.put("message", message);
            resultMap.put("stepStaff", stepStaff);

        } else if ("message".equals(queryMethod)) {
            resultMap = new HashMap<>();


            ApMessage apMessageParam = new ApMessage();
            apMessageParam.setMessageId(queryId);

            Message message = messageDAO.selectMessageById(queryId);
            System.out.println(message);
            ApMessage apMessage = messageDAO.selectApMessageByApMessage(apMessageParam);
            System.out.println(apMessage);
            Event event = templateDAO.selectTemplateById(apMessage.getEventId());
            System.out.println(event);
            Process process = processDAO.selectProcessById(event.getCurrentStepId());
            StepStaff stepStaff = stepStaffDAO.selectByStepIdAndStaffId(event.getCurrentStepId(), apMessage.getStaffNo());

            resultMap.put("event", event);
            resultMap.put("currentStep", process);
            resultMap.put("message", message);
            resultMap.put("stepStaff", stepStaff);


        } else {
            return new ResponseUtil<>(0, "查询方法不正确");
        }

        return new ResponseUtil<>(1, "查询成功", resultMap);


    }

    @Override
    public ResponseUtil<Map<String, Object>> getEventAllProcessCreatorAuditor(String eventId) {

        Event event = templateDAO.selectTemplateById(eventId);
        List<Process> processList = processDAO.selectProcessBelongEvent(eventId);
        List<EventCreator> creatorList = eventCreatorDAO.selectEventCreatorByTemplateId(eventId);


        Map<String, Object> processMap;
        Map<String, Object> resultMap = new HashMap<>();
        List<Map<String, Object>> mapList = new ArrayList<>();
        resultMap.put("event", event);
        resultMap.put("creator", creatorList);

        List<StepStaff> stepStaffList;
        for (Process p : processList) {
            processMap = new HashMap<>();
            processMap.put("process", p);
            stepStaffList = stepStaffDAO.selectStepStaffsByProcessId(p.getId());
            processMap.put("stepStaff", stepStaffList);
            mapList.add(processMap);
        }

        resultMap.put("processList", mapList);

        return new ResponseUtil<>(1, "查询成功", resultMap);
    }


    private void sendMessageToEventCreatorForReject(Event event,String auditorId){
        List<EventCreator> eventCreatorList  = eventCreatorDAO.selectEventCreatorByTemplateId(event.getId());
        List<Message> messageList = new LinkedList<>();

        for(EventCreator eventCreator:eventCreatorList){
            Message message = new Message();
            message.setId(IDNOUtil.getIDNO());
            message.setFromUser(auditorId);
            message.setToUser(eventCreator.getCreatorNo());
            message.setSubject("采购申请立项[审批通知]");
            message.setContent("审批事件 "+event.getId()+" 被拒绝");
            message.setSendTime(new Timestamp(System.currentTimeMillis()));
            message.setHaveRead("0");
            message.setMessageType("0");
            messageDAO.insertMessage(message);
        }
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

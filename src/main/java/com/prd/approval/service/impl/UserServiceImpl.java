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

        //List<Event> eventList = templateDAO.selectTodoEventListByUserId(userId);
        List<Message> messageList = messageDAO.selectAllMessageToUser(userId, null);
        if (messageList.size() == 0) {
            return new ResponseUtil<>(1, "暂无消息");
        }
        return new ResponseUtil<>(1, "您有新的消息");

    }

    @Override
    public ResponseUtil<List<Message>> getMessage(String toUserId, String messageType) {
        //  String stepStaffId = messageDAO.selectApMessageByUserId(toUserId);
        List<Message> messageList = messageDAO.selectAllMessageToUser(toUserId, messageType);
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

    /*
     event: status  1 新建， 3 通过审批，  4 被拒绝， 5 被中止
     process :status 1 新建，2 正在执行(开启),3 通过，4 = 被拒绝
     result：未审批notExamine 通过pass 未通过notPass
    */
    @Transactional
    @Override
    public ResponseUtil<Event> doApproval(String eventId, String result, String remarks, String auditorId) {
        Event event = templateDAO.selectTemplateById(eventId);
        Process current = processDAO.selectProcessById(event.getCurrentStepId());
        StepStaff stepStaff = stepStaffDAO.selectByStepIdAndStaffId(current.getId(), auditorId);
        String msg;
        if ((msg = doApprovalArgsWrong(event, stepStaff)) != null) {
            return new ResponseUtil<>(1, msg);
        }
        ResponseUtil<Event> response;
        switch (result) {
            case "pass": {
                response = approvalPass(event, current, stepStaff, remarks, auditorId);
                break;
            }
            case "notPass": {
                response = approvalNotPass(event, current, stepStaff, remarks, auditorId);
                break;
            }
            default: {
                response = new ResponseUtil<>(0, "审批结果不正确");
            }
        }
        return response;

    }

    private String doApprovalArgsWrong(Event event, StepStaff stepStaff) {
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
        if (stepStaff == null) {
            msg = "数据异常，找不到审批阶段" + event.getCurrentStepId() + " 和审批人对应的审批步骤(stepStaff)";
        }
        return msg;
    }


    @Override
    public ResponseUtil<Map<String, Object>> getTargetBill(String applyNo) {

        Map<String, Object> resultMap = applyHeaderDAO.selectApplyHeaderByApplyNo(applyNo);

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

        List<Map<String, Object>> resultMapList = templateDAO.selectEventByCase(billNo, creator, eventStatus);

        if (resultMapList.size() == 0) {
            return new ResponseUtil<>(1, "查询成功，结果为空");
        }
        return new ResponseUtil<>(1, "查询成功", resultMapList);

    }


    @Transactional
    @Override
    public ResponseUtil<Map<String, Object>> getEventProcessCreator(Map<String, String> map) {
        String queryMethod = map.get("queryMethod");
        String queryId = map.get("queryId");
        Map<String, Object> resultMap = new HashMap<>();
        if ("stepStaff".equals(queryMethod)) {

            StepStaff stepStaff = stepStaffDAO.selectByStepStaffId(queryId);
            Process process = processDAO.selectProcessById(stepStaff.gethId());
            Event event = templateDAO.selectTemplateById(process.getEventId());

            resultMap.put("event", event);
            resultMap.put("currentStep", process);
            resultMap.put("stepStaff", stepStaff);

        } else if ("message".equals(queryMethod)) {

            ApMessage apMessageParam = new ApMessage();
            apMessageParam.setMessageId(queryId);
            ApMessage apMessage = messageDAO.selectApMessageByApMessage(apMessageParam);
            System.out.println(apMessage);
            Event event = templateDAO.selectTemplateById(apMessage.getEventId());
            System.out.println(event);
            if (event == null) {
                return new ResponseUtil<>(0, "该目标单据数据错误");
            }
            Process process = processDAO.selectProcessById(apMessage.getStepId());
            StepStaff param = new StepStaff();
            param.sethId(apMessage.getStepId());
            param.setStaffNo(apMessage.getStaffNo());
            StepStaff stepStaff = stepStaffDAO.selectByStepStaff(param);
            System.out.println(stepStaff);
            resultMap.put("event", event);
            resultMap.put("currentStep", process);
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

    @Override
    public ResponseUtil<Message> messageHaveRead(String messageId) {
        Message message = new Message();
        message.setId(messageId);
        message.setHaveRead("1");
        int effect = messageDAO.updateMessage(message);
        if (effect != 0) {
            return new ResponseUtil<>(1, "修改成功");
        }
        return new ResponseUtil<>(0, "修改失败");
    }

    /*审批事件 被拒绝或通过时，给事件的发起人发送 拒绝或通过的普通消息 */
    private void sendMessageToEventCreatorForResult(Event event, String auditorId, String result) {
        System.out.println("event "+event);
        List<EventCreator> eventCreatorList = eventCreatorDAO.selectEventCreatorByTemplateId(event.getModelId());


        System.out.println("给事件的发起人发送");
        System.out.println(eventCreatorList.size());
        for (EventCreator eventCreator : eventCreatorList) {

            Message message = new Message();
            message.setId(IDNOUtil.getIDNO());
            message.setFromUser("000");
            message.setToUser(eventCreator.getCreatorNo());
            message.setSubject("采购申请立项[审批通知]");
            message.setContent("审批事件 " + event.getEventName() + " (" + event.getId() + ") " + result);
            message.setSendTime(new Timestamp(System.currentTimeMillis()));
            message.setHaveRead("0");
            message.setMessageType("0");
            System.out.println("插入普通消息");
            messageDAO.insertMessage(message);
        }
    }

    /*审批 通过
     *
     * event status  =1 新建， = 3 通过审批， = 4 被拒绝， = 5 被中止
     * process status 1=新建，2=  正在执行(开启),3= 通过，4 = 被拒绝
     * stepStaff result  1 = 未审批， 3 = 审批通过， 4 = 拒绝
     * stepStaff status 1 = 未执行，2 = 已执行(通过或拒绝)
     *
     * */
    private ResponseUtil<Event> approvalPass(Event event, Process current, StepStaff stepStaff, String remarks, String auditorId) {
        ResponseUtil<Event> responseUtil;
        int remain = (current.getTimesRemain() - 1) < 0 ? 0 : (current.getTimesRemain() - 1);
        current.setStatus("2");
        current.setTimesRemain(remain);
        stepStaff.setApComment(remarks);
        stepStaff.setApResult("3");
        stepStaff.setStatus("2");
        stepStaff.setApDate(new Timestamp(System.currentTimeMillis()));
        if (current.getTimesRemain() == 0) {
            if (hasNextStep(current)) {
                Process next = processDAO.selectNextProcess(current);
                //1
                event.setCurrentStepId(next.getId());
                //2
                event.setCurrentStepSortNo(next.getSortNo());
                //3
                next.setStatus("2");
                //4 更新message表
                messageDAO.updateMessageByEventIdAndLastStepSortNo(event.getId(),current.getSortNo());
                //5  当前审批阶段结束时，需要向message 和 apMessage 插入下一阶段的审批消息
                insertApprovalMessage(event);
                System.out.println("进入下一阶段");
                responseUtil = new ResponseUtil<>(1, "执行审批阶段 " + current.getId() + " 成功,审批事件进入下一审批阶段：" + next.getId());
                /* 整个事件审批结束 ，阶段全部通过，该事件的最后一个阶段执行结束*/
            } else {
                System.out.println("整个事件审批结束");
                event.setStatus("3");
                applyHeaderDAO.updateStatusByApplyNo("4", event.getBillNo());
                messageDAO.cancelOtherAlert(event.getId());
                //整个审批事件结束，给事件发起人发个普通消息
                sendMessageToEventCreatorForResult(event, auditorId, "审批通过");

                responseUtil = new ResponseUtil<>(1, "审批事件 " + event.getId() + " 全部执行结束");
            }
        } else {
            System.out.println("该阶段继续审批");
            responseUtil = new ResponseUtil<>(1, "执行审批阶段 " + current.getId() + " 成功，该阶段剩余审批次数：" + current.getTimesRemain());
        }
        messageDAO.updateMessageByApMessageStaffId(stepStaff.getId());
        templateDAO.updateTemplate(event);
        processDAO.updateProcess(current);
        stepStaffDAO.updateStepStaffByStepIdAndStaffId(stepStaff);

        return responseUtil;

    }

    /**
     * 当前审批阶段结束时，需要向message 和 apMessage 插入下一阶段的审批消息
     * 消息接收者为 下一阶段的审批人
     * @param event
     */
    private void insertApprovalMessage(Event event) {

        List<StepStaff> stepStaffList = stepStaffDAO.selectStepStaffsByProcessId(event.getCurrentStepId());
        for(StepStaff stepStaff:stepStaffList){
            Message message = new Message();
            message.setId(IDNOUtil.getIDNO());
            message.setSubject("审批通知");
            message.setContent("["+event.getBillName()+"]"+"("+event.getBillNo()+") 需要审批");
            message.setToUser(stepStaff.getStaffNo());
            message.setFromUser("System");
            message.setSendTime(new Timestamp(System.currentTimeMillis()));
            message.setMessageType("ap");
            message.setHaveRead("0");
            message.setHaveDone("0");
            message.setHaveAlert("0");
            messageDAO.insertMessage(message);


            ApMessage apMessage = new ApMessage();
            apMessage.setId(IDNOUtil.getIDNO());
            apMessage.setMessageId(message.getId());
            apMessage.setEventId(event.getId());
            // todo
            apMessage.setStepId(event.getCurrentStepId());
            apMessage.setStaffNo(stepStaff.getStaffNo());
            apMessage.setStaffId(stepStaff.getId());
            messageDAO.insertApMessage(apMessage);
        }
    }

    /* 审批  拒绝
     *
     * stepStaff result 1 = 未审批， 3 = 审批通过， 4 = 拒绝
     * stepStaff status 1 = 未执行，2 = 已执行(通过或拒绝)
     * */
    private ResponseUtil<Event> approvalNotPass(Event event, Process current, StepStaff stepStaff, String remarks, String auditorId) {
        // 1
        event.setStatus("4");
        // 2
        stepStaff.setApComment(remarks);
        stepStaff.setApResult("4");
        stepStaff.setStatus("2");
        stepStaff.setApDate(new Timestamp(System.currentTimeMillis()));
        current.setTimesRemain(current.getTimesRemain() - 1);

        if (eventHasBeenRejected(current,stepStaff)) {
            current.setStatus("4");
            event.setStatus("4");
            //5 发送审批不通过的消息给事件的发起人
            sendMessageToEventCreatorForResult(event, auditorId, "被拒绝");
        }
        //3
        applyHeaderDAO.updateStatusByApplyNo("3", event.getBillNo());
        stepStaffDAO.updateStepStaffByStepIdAndStaffId(stepStaff);
        templateDAO.updateTemplate(event);
        //4
        messageDAO.updateMessageByApMessageStaffId(stepStaff.getId());

        processDAO.updateProcess(current);
        return new ResponseUtil<>(1, "拒绝审批阶段成功");
    }


    private boolean hasNextStep(Process current) {
        return processDAO.selectNextProcess(current) != null;
    }

    // 如果未审批的人数小于需要通过的次数，false
    private boolean eventHasBeenRejected(Process current,StepStaff stepStaff) {
        int personRemainNum = stepStaffDAO.selectStepUnApprovalPersonNumber(stepStaff.getId());
        return current.getTimesRemain() < personRemainNum;

    }


}

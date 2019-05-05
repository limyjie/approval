package com.prd.approval.service;


import com.prd.approval.entity.ApplyHeader;
import com.prd.approval.entity.Event;
import com.prd.approval.entity.Message;
import com.prd.approval.entity.User;
import com.prd.approval.utils.ResponseUtil;

import java.util.List;
import java.util.Map;

public interface UserService {

    ResponseUtil<User> login(User webUser);

    ResponseUtil<List<Event>> checkMessage(String userId);

    ResponseUtil<List<Message>> getMessage(String toUserId);

    ResponseUtil<Message> sendMessage(Message message);

    ResponseUtil<Map<String,Object>> getApprovalEvent(String eventId);

    ResponseUtil<Event> doApproval(String eventId,String result,String remarks,String auditorId);

    ResponseUtil<ApplyHeader> getTargetBill(String processId);

    ResponseUtil<Map<String,Object>> getEventByIdAndUser(String eventId);

    ResponseUtil<List<Map<String,Object>>> getEventByCase(String billNo,String creator,String eventStatus);

    ResponseUtil<Map<String,Object>> getEventProcessCreator(String eventId);

    ResponseUtil<Map<String,Object>> getEventAllProcessCreatorAuditor(String eventId);
}

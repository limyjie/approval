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

    ResponseUtil<List<Message>> checkMessage(String userId);

    ResponseUtil<List<Message>> getMessage();

    ResponseUtil<Message> sendMessage(Message message);

    ResponseUtil<Map<String,Object>> getApprovalEvent(String eventId);

    ResponseUtil<Event> doApproval(String eventId,String result,String remarks);

    ResponseUtil<ApplyHeader> getTargetBill(String id);
}

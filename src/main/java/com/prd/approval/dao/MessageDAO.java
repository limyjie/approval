package com.prd.approval.dao;

import com.prd.approval.entity.Message;

import java.util.List;

public interface MessageDAO {


    /**
     * <p>查询message 里面 message type 为 ap 的 行</p>
     * @return
     */
    List<Message> selectAllApprovalMessage();

    int insertMessage(Message message);

}

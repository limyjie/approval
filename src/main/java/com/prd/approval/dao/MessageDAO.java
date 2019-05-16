package com.prd.approval.dao;


import com.prd.approval.entity.ApMessage;
import com.prd.approval.entity.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.lang.Nullable;

import java.util.List;

public interface MessageDAO {


    /**
     * <p>查询message 里面 message type 为 ap 的 行</p>
     *
     * @return
     */
    List<Message> selectAllApprovalMessage();

    int insertMessage(Message message);

    int insertMessageList(List<Message> messageList);

    List<Message> selectAllMessageToUser(String toUser);

    Message selectMessageByEventId(String eventId);

    Message selectMessageById(String messageId);

    ApMessage selectApMessageByApMessage(ApMessage apMessage);
}

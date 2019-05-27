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



    List<Message> selectAllMessageToUser(@Param("toUser")String toUser,
                                         @Param("messageType")String messageType);

    Message selectMessageByEventId(String eventId);

    Message selectMessageById(String messageId);

    ApMessage selectApMessageByApMessage(ApMessage apMessage);

    int updateMessage(Message message);

    ApMessage selectApMessageByMessageId(String toUserId);

    int updateMessageByApMessageStaffId(String stepStaffId);

    int cancelOtherAlert(String eventId);

    int updateMessageByEventIdAndLastStepSortNo(@Param("eventId")String eventId,
                                                @Param("lastStepSortNo")Integer lastStepSortNo);

    int insertApMessage(ApMessage apMessage);
}

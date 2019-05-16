package com.prd.approval.dao;

import com.prd.approval.entity.Event;
import com.prd.approval.entity.EventCreator;
import com.prd.approval.entity.Message;
import com.prd.approval.utils.IDNOUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageDAOTest {


    @Autowired
    private MessageDAO messageDAO;

    @Autowired
    private EventCreatorDAO eventCreatorDAO;

    @Autowired
    private TemplateDAO templateDAO;

    @Test
    public void insertMessageList() {

        String auditorId = "101";
        String eventId  =  "57692";
        Event event = templateDAO.selectTemplateById(eventId);
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
            message.setMessageType("ap");
            messageList.add(message);
        }
        messageDAO.insertMessageList(messageList);
    }
}
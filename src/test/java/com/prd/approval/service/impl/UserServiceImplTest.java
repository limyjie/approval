package com.prd.approval.service.impl;

import com.prd.approval.dao.TemplateDAO;
import com.prd.approval.entity.EventCreator;
import net.bytebuddy.asm.Advice;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private TemplateDAO templateDAO;

    @Ignore
    @Test
    public void getEventByCase() {
        String billNo = null;
        String creator = "109";
        String status = null;

        List<Map<String, Object>> mapList = templateDAO.selectEventAndOriginatorByCase(billNo, creator, status);
        List<Map<String, Object>> finalList = new LinkedList<>();
        List<EventCreator> eventCreatorList;
        if (creator != null) {
            for (Map<String, Object> map : mapList) {
                boolean containCreator = false;
                eventCreatorList = (List<EventCreator>) map.get("creatorList");
                for (EventCreator eventCreator : eventCreatorList) {
                    if (eventCreator.getCreatorNo().equals(creator)) {
                        containCreator = true;
                        break;
                    }
                }
                if(containCreator){
                    System.out.println("add: "+map);
                    finalList.add(map);
                }
            }
        }else{
            finalList = mapList;
        }
        System.out.println(finalList.size());
        for (Map<String, Object> map : finalList) {
            System.out.println(map);
        }
    }


    public void sendMessageToEventCreatorForReject(){

    }
}
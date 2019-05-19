package com.prd.approval.dao;

import com.prd.approval.entity.EventCreator;
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
public class TemplateDAOTest {

    @Autowired
    private TemplateDAO templateDAO;

    @Test
    public void test(){

        String creator = null;
        String billNo = null;
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
                        System.out.println();
                        break;
                    }
                }
                if (containCreator) {
                    System.out.println("add: " + map);
                    finalList.add(map);
                }
            }
        } else {
            finalList = mapList;
        }
        for(Map<String,Object> map:mapList){
            System.out.println(map);
        }

    }

}
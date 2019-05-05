package com.prd.approval.dao;

import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TemplateDAOTest {

    @Autowired
    private TemplateDAO templateDAO;

    @Test
    public void selectEventByEventIdAndUserId() {

        String eventId = "57692";
        Map map = templateDAO.selectEventAndCreatorAndCurrentProcess(eventId);
        System.out.println(map);


    }
}
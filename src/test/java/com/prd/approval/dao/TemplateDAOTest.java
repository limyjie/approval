package com.prd.approval.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TemplateDAOTest {

    @Autowired
    private TemplateDAO templateDAO;

    @Test
    public void selectEventAndOriginatorByCase() {
       List<Map<String,Object>>  mapList= templateDAO.selectEventAndOriginatorByCase(null,null,null);
        for(Map<String,Object> m:mapList){
            System.out.println(m);
        }
    }
}
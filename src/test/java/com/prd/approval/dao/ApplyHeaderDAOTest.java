package com.prd.approval.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ApplyHeaderDAOTest {

    @Autowired
    private ApplyHeaderDAO applyHeaderDAO;

    @Test
    public void selectApplyHeaderByApplyNo() {
        String applyNo = "PA000000001";
        Map<String,Object> resultMap = applyHeaderDAO.selectApplyHeaderByApplyNo(applyNo);
        System.out.println(resultMap);
    }
}
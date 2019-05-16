package com.prd.approval.dao;

import com.prd.approval.entity.ApplyHeader;
import com.prd.approval.entity.ApplyList;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplyListDAOTest {

    @Autowired
    private ApplyHeaderDAO applyHeaderDAO;

    @Autowired
    private ApplyListDAO applyListDAO;

    @Test
    public void selectApplyListByHID() {
        String applyNo = "PA000000001";

        Map<String,Object> header = applyHeaderDAO.selectApplyHeaderByApplyNo(applyNo);
        List<ApplyList> list = applyListDAO.selectApplyListByHID(applyNo);


    }
}
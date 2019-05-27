package com.prd.approval.service.impl;

import com.prd.approval.dao.ApplyHeaderDAO;
import com.prd.approval.dao.ApplyListDAO;
import com.prd.approval.dao.OtherDAO;
import com.prd.approval.dao.StepStaffDAO;
import com.prd.approval.entity.StepStaff;
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
public class UserServiceImplTest {

    @Autowired
    private ApplyHeaderDAO applyHeaderDAO;

    @Autowired
    private OtherDAO otherDAO;


    @Autowired
    private StepStaffDAO stepStaffDAO;
    @Autowired
    private ApplyListDAO applyListDAO;

    @Test
    public void getTargetBill() {

        String userId = "101";
        String status = "all";
        List<StepStaff> stepStaffList = stepStaffDAO.selectByStaffIdAndStatus(userId,status);

    }
}
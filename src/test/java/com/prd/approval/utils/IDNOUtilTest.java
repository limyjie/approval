package com.prd.approval.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class IDNOUtilTest {



    @Test
    public void getMessageSera() {

        System.out.println(IDNOUtil.getMessageSera("101"));
    }
}
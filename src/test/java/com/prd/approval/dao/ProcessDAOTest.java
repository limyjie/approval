package com.prd.approval.dao;

import com.prd.approval.entity.Process;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProcessDAOTest {

    @Autowired
    private ProcessDAO processDAO;

    @Test
    public void updateProcess() {

        if(processDAO==null){
            System.out.println("error");
        }
        Process process = new Process();
        process.setId("517211");
        process.setStepName("name");
        int result = processDAO.updateProcess(process);
        System.out.println(result);

    }
}
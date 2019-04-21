package com.prd.approval.dao;


import com.prd.approval.entity.Process;
import com.prd.approval.entity.User;

import java.util.List;

public interface ProcessDAO {



    int insertProcess(Process process);

    List<Process> selectAllProcess();

    int deleteProcessById(String id);

    Process selectProcessById(String id);

    int updateProcess(Process process);

    List<Process> selectProcessBelongTemplate(String templateId);
}

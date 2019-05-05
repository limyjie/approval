package com.prd.approval.dao;

import com.prd.approval.entity.Auditor;

import java.util.List;

public interface AuditorDAO {

    /*
    * 查询所有审批人
    * */
    List<Auditor> selectAllAuditor();

    /*
    * 查询所有审批人
    * id,name,department,
    * 其中，department = TCCOM001.depa + FI023.dsca
    *
    * */
    List<Auditor> selectAllAuditor4Frontend();



    Auditor selectAuditorById(String id);

    String selectDepartmentNameById(String departmentId);
}

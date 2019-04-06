/**
 * Author: lin
 * Date: 2019/3/27 13:20
 */
package com.prd.approval.service.impl;

import com.prd.approval.dao.AuditorDAO;

import com.prd.approval.entity.Auditor;
import com.prd.approval.service.AuditorService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditorServiceImpl implements AuditorService {

    @Autowired
    private AuditorDAO auditorDAO;

    @Override
    public ResponseUtil<List<Auditor>> findAllAuditor() {
        List<Auditor> auditors = auditorDAO.selectAllAuditor4Frontend();
        if(auditors==null){
            return new ResponseUtil<>(0,"审批人查找失败");
        }

        return new ResponseUtil<>(1,"审批人查找成功",auditors);
    }
}

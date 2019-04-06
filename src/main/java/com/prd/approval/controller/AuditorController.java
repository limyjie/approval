/**
 * Author: lin
 * Date: 2019/4/3 15:50
 */
package com.prd.approval.controller;


import com.prd.approval.entity.Auditor;
import com.prd.approval.service.AuditorService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
* 审批人
*
* */

@RestController
@RequestMapping("/auditor")
public class AuditorController {

    @Autowired
    private AuditorService auditorService;

    /*
    * 获取审批人列表
    *
    * */
    @GetMapping("/getAll")
    public ResponseUtil<List<Auditor>> getAll(){
        return auditorService.findAllAuditor();
    }
}

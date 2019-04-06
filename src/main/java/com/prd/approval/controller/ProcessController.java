/**
 * Author: lin
 * Date: 2019/3/27 12:45
 */
package com.prd.approval.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.prd.approval.entity.Process;
import com.prd.approval.service.ProcessService;
import com.prd.approval.utils.LogUtil;
import com.prd.approval.utils.ResponseUtil;
import com.prd.approval.validation.ProcessValid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import sun.rmi.runtime.Log;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/process")
public class ProcessController {

    @Autowired
    private ProcessService processService;


    /*
     * @RequestBody 接收的是JSON 字符串！
     *
     * */
    @PostMapping("/add")
    public ResponseUtil<Process> addProcess(@RequestBody Map<String, Object> map) {

        Process process = JSON.parseObject(JSON.toJSONString(map.get("process")), Process.class);
        ArrayList<Integer> arrayList = JSONArray.parseObject(JSON.toJSONString(map.get("list")), ArrayList.class);

        return processService.addProcess(process, arrayList);
    }

    @GetMapping("/getAll")
    public ResponseUtil<List<Process>> getAllProcess() {
        return processService.getAll();
    }


}

/**
 * Author: lin
 * Date: 2019/3/27 12:45
 */
package com.prd.approval.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;
import com.prd.approval.entity.Process;
import com.prd.approval.service.ProcessService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        List param = (List)map.get("list");
        /*list 可能 为 空：[] */
        if(param.isEmpty()){
            return new ResponseUtil<>(0,"请添加审批人");
        }
        if (process.getTimesCount()==null){
            return new ResponseUtil<>(0,"通过次数不能为空");
        }
        ArrayList<Integer> arrayList = JSONArray.parseObject(
                JSON.toJSONString(param),
                new TypeReference<ArrayList<Integer>>(){});

        return processService.addProcess(process, arrayList);
    }

    @GetMapping("/getAll")
    public ResponseUtil<List<Process>> getAllProcess() {
        return processService.getAll();
    }

    @GetMapping("/get/{id}")
    public ResponseUtil<Map<String, Object>> getProcess(@PathVariable("id") String processId) {

        return processService.getProcess(processId);
    }

    @PostMapping("/remove/{id}")
    public ResponseUtil<List<Process>> removeByProcessId(@PathVariable("id") String processId) {
        return processService.removeProcess(processId);
    }

    @PostMapping("/modify")
    public ResponseUtil<Process> modifyProcess(@RequestBody Map<String, Object> map) {
        Process process = JSON.parseObject(JSON.toJSONString(map.get("process")), Process.class);
        ArrayList<Integer> arrayList = JSONArray.parseObject(JSON.toJSONString(map.get("list")),
                new TypeReference<ArrayList<Integer>>(){});
        return processService.modifyProcess(process, arrayList);
    }

}

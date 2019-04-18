/**
 * Author: lin
 * Date: 2019/4/6 17:11
 */
package com.prd.approval.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.prd.approval.entity.Event;
import com.prd.approval.service.TemplateService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 *
 * </p>
 */

@RestController
@RequestMapping("/template")
public class TemplateController {

    @Autowired
    private TemplateService templateService;

    /**
     * <p>定义【发起人】、【单据类型】、选择【审批阶段】</p>
     *
     * @param
     * @return
     */
    @PostMapping("/add")
    public ResponseUtil<Event> add(@RequestBody Map map, HttpServletRequest request) {
        Event event = JSON.parseObject(JSON.toJSONString(map.get("template")), Event.class);
        ArrayList<Integer> processIdStringList = JSONArray.parseObject(JSON.toJSONString(map.get("processIdList")), ArrayList.class);
        ArrayList<Integer> originatorIdStringList = JSONArray.parseObject(JSON.toJSONString(map.get("originatorIdList")), ArrayList.class);


        ArrayList<String> processIdList = new ArrayList<>();
        ArrayList<String> originatorIdList = new ArrayList<>();

        for (Integer integer : processIdStringList) {
            processIdList.add(integer.toString());
        }
        for (Integer integer : originatorIdStringList) {
            originatorIdList.add(integer.toString());
        }

        return templateService.addTemplate(event, processIdList, originatorIdList);
    }

    @GetMapping("/getAll")
    public ResponseUtil<List<Event>> getAll() {
        return templateService.findAllTemplate();

    }


    @GetMapping("/get/{id}")
    public ResponseUtil<Event> findTemplate(@PathVariable("id") String templateId) {
        return templateService.findTemplate(templateId);
    }

    @GetMapping("/getAll/active")
    public ResponseUtil<List<Event>> findALlActive() {
        return templateService.findAllActiveTemplate();
    }

    @PostMapping("/remove/{id}")
    public ResponseUtil<Event> removeTemplate(@PathVariable("id") String templateId) {
        return templateService.removeTemplate(templateId);
    }

    @PostMapping("/modify")
    public ResponseUtil<Event> modifyTemplate(@RequestBody Map map) {
        Event event = JSON.parseObject(JSON.toJSONString(map.get("template")), Event.class);
        ArrayList<Integer> originatorIdList = JSONArray.parseObject(JSON.toJSONString(map.get("originatorIdList")), ArrayList.class);
        ArrayList<Integer> processIdList = JSONArray.parseObject(JSON.toJSONString(map.get("processIdList")), ArrayList.class);

        ArrayList<String> originatorIdStrList = new ArrayList<>();
        ArrayList<String> processIdStrList = new ArrayList<>();
       for(Integer i:originatorIdList){
           originatorIdStrList.add(i.toString());
       }
       for(Integer i:processIdList){
           processIdStrList.add(i.toString());
       }
        System.out.println(event.toString());
        return templateService.modifyTemplate(event, originatorIdStrList, processIdStrList);
    }
}

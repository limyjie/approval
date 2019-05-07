/**
 * Author: lin
 * Date: 2019/4/6 17:11
 */
package com.prd.approval.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.prd.approval.entity.Event;
import com.prd.approval.service.TemplateService;
import com.prd.approval.utils.JavaTypeUtil;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
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


    private ObjectMapper mapper;

    @Autowired
    public TemplateController(ObjectMapper mapper) {
        this.mapper = mapper;
    }

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
    public ResponseUtil<Map<String, Object>> findTemplate(@PathVariable("id") String templateId) {
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
    public ResponseUtil<Event> modifyTemplate(@RequestBody Map map) throws IOException {
        Event event = JSON.parseObject(JSON.toJSONString(map.get("template")), Event.class);


        //     JavaType type = getCollectionType(map.get("originatorIdList").getClass(),Integer.class);
        //   mapper.getTypeFactory().
        //     String jsonInput = map.get("originatorIdList").toString();
        //    List<Integer> originatorIdList = mapper.readValue(jsonInput,type);
        ArrayList<Integer> originatorIdList = JSONArray.parseObject(JSON.toJSONString(map.get("originatorIdList")), ArrayList.class);
        ArrayList<Integer> processIdList = JSONArray.parseObject(JSON.toJSONString(map.get("processIdList")), ArrayList.class);

        ArrayList<String> originatorIdStrList = new ArrayList<>();
        ArrayList<String> processIdStrList = new ArrayList<>();
        for (Integer i : originatorIdList) {
            originatorIdStrList.add(i.toString());
        }
        for (Integer i : processIdList) {
            processIdStrList.add(i.toString());
        }
        System.out.println(event.toString());
        return templateService.modifyTemplate(event, originatorIdStrList, processIdStrList);

    }

    public JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 执行审批事件窗口（只能查询到登陆者的记录，其他人的无法看到）
     *      需求：根据已执行、全部、未执行查询审批事件
     * 传给后台数据：已执行、全部、未执行
     * @param map
     * @return
     */
    @PostMapping("/event/byStatus")
    public ResponseUtil<List<Map<String,Object>>> getEventByStatus(@RequestBody Map<String,String> map){
        String status = map.get("status");
        String userId = map.get("userId");
        if(userId == null || userId.trim().isEmpty()){
            return new ResponseUtil<>(0,"用户编码不能为空");
        }
        if("done".equals(status) || "all".equals(status) || "todo".equals(status)){
            return templateService.getEventByStatusAndUser(status,userId);
        }
        return new ResponseUtil<>(0,"审批事件状态错误");
    }
}

/**
 * Author: lin
 * Date: 2019/3/24 15:39
 */
package com.prd.approval.controller;


import com.prd.approval.entity.ApplyHeader;
import com.prd.approval.entity.Event;
import com.prd.approval.entity.Message;
import com.prd.approval.entity.User;
import com.prd.approval.service.UserService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseUtil<User> login(@RequestBody User webUser){

        return userService.login(webUser);
    }

    /**
     * <p>用户登录后，返回需要审批的事件</p>
     * @param userID
     * @return
     */
    @PostMapping("/checkMessage/{userId}")
    public ResponseUtil<List<Event>> checkMessage(@PathVariable("userId")String userID){
        return userService.checkMessage(userID);
    }

    @GetMapping("/getApMessage")
    public ResponseUtil<List<Message>> getMessage(){
        return userService.getMessage();
    }


    @PostMapping("/sendMessage")
    public ResponseUtil<Message> postMessage(@RequestBody Message message){

        return userService.sendMessage(message);
    }

    @GetMapping("/getEvent/{id}")
    public ResponseUtil<Map<String,Object>> getApprovalEvent(@PathVariable("id")String eventId){
        return userService.getApprovalEvent(eventId);
    }

    // 执行审批
    @PostMapping("/doApproval")
    public ResponseUtil<Event> doApproval(@RequestBody Map<String,Object> map){
        String eventId = map.get("eventId").toString();
        String result = map.get("result").toString();
        String remarks = map.get("remarks").toString();
        String auditorId = map.get("auditorId").toString();
        return userService.doApproval(eventId,result,remarks,auditorId);
    }

    @GetMapping("/getTargetBill/{id}")
    public ResponseUtil<ApplyHeader> getTargetBill(@PathVariable("id")String id ){
        return userService.getTargetBill(id);
    }



}

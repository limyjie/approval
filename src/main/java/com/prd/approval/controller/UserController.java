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
import javax.validation.Valid;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*允许跨域请求*/
@CrossOrigin(origins = "*", maxAge = 3600)
/*返回JSON*/
@RestController
/*此类接收的请求都以/user 开始 */
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    //@PostMapping("/login")
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseUtil<Map<String, String>> login(@RequestBody User webUser) {

        return userService.login(webUser);
    }

    /**
     * <p>用户登录后，返回需要审批的事件</p>
     *
     * @param userID
     * @return
     */
    @PostMapping("/checkMessage/{userId}")
    public ResponseUtil<List<Event>> checkMessage(@PathVariable("userId") String userID) {
        return userService.checkMessage(userID);
    }

    @GetMapping("/getMessage")
    public ResponseUtil<List<Message>> getMessage(
            @RequestParam(value = "id",required = true) String userId,
            @RequestParam(value = "type",required = false)String messageType) {
        return userService.getMessage(userId,messageType);
    }


    @PostMapping("/sendMessage")
    public ResponseUtil<Message> postMessage(@RequestBody Message message) {
        return userService.sendMessage(message);
    }

    @GetMapping("/getEvent/{id}")
    public ResponseUtil<Map<String, Object>> getApprovalEvent(@PathVariable("id") String eventId) {
        return userService.getApprovalEvent(eventId);
    }

    // 执行审批
    @PostMapping("/doApproval")
    public ResponseUtil<Event> doApproval(@RequestBody Map<String, String> map) {
        System.out.println("map:  "+map);

        String eventId = map.get("eventId");
        String result = map.get("result");
        String remarks = map.get("remarks");
        String auditorId = map.get("auditorId");
        if(eventId == null || eventId.trim().isEmpty() ||
                result == null || result.trim().    isEmpty() ||
                auditorId == null || auditorId.trim().isEmpty()){
            return new ResponseUtil<>(0,"参数不能为空");
        }
        return userService.doApproval(eventId, result, remarks, auditorId);
    }

    @GetMapping("/getTargetBill/{id}")
    public ResponseUtil<Map<String,Object>> getTargetBill(@PathVariable("id") String id) {
        return userService.getTargetBill(id);
    }

    /**
     * <p>
     * 需求：根据事件ID获取事件
     * 获取数据：事件名、描述、目标单据、发起人、创建日期
     * </p>
     *
     * @param
     * @return
     */
    @GetMapping("/event/{eventId}")
    public ResponseUtil<Map<String, Object>> getEvent(@PathVariable("eventId") String eventId) {

        return userService.getEventByIdAndUser(eventId);
    }


    /**
     * 查看审批记录（可以看到所有记录）
     * 需求：根据单据编号、发起人、事件状态等任意某个或多个数据查询审批记录
     * 获取数据：发起人、事件名[]
     *
     * @param map
     * @return
     */
    @PostMapping("/event/case")
    public ResponseUtil<List<Map<String, Object>>> getByCase(@RequestBody Map<String, String> map) {
        String billNo = map.get("billNo");
        String creator = map.get("creator");
        String eventStatus = map.get("status");
        return userService.getEventByCase(billNo, creator, eventStatus);
    }

    /**
     * 根据 id  返回 事件、发起人、当前执行的阶段、以及 step staff
     * 这个id 可以是 messageId，也可以是 step staff id
     *
     * @param map
     * @return
     */
    @PostMapping("/event/detail")
    public ResponseUtil<Map<String, Object>> getEventProcessCreator(@RequestBody Map<String, String> map) {

        return userService.getEventProcessCreator(map);
    }

    @GetMapping("/event/allDetail/{stepStaffId}")
    public ResponseUtil<Map<String, Object>> getEventAllProcessCreatorAuditor(@PathVariable("stepStaffId") String stepStaffId) {
        return userService.getEventAllProcessCreatorAuditor(stepStaffId);
    }


    @PostMapping("/message/haveRead")
    public ResponseUtil<Message> messageHaveRead(@RequestBody String messageId){
        return userService.messageHaveRead(messageId);
    }


}

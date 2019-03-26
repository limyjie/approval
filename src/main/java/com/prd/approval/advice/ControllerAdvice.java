/**
 * Author: lin
 * Date: 2019/3/24 16:19
 */
package com.prd.approval.advice;

import com.prd.approval.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@org.springframework.web.bind.annotation.ControllerAdvice(
        basePackages = {"com.prd.approval.controller.*"},
        annotations = {Controller.class, RestController.class}
)
public class ControllerAdvice {

    @ExceptionHandler(value = NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> exception(HttpServletRequest request,NotFoundException ex){
        Map<String,Object> map = new HashMap<>();
        map.put("status",ex.getStatus());
        map.put("msg",ex.getMsg());
        return map;
    }
}

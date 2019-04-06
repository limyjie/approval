/**
 * Author: lin
 * Date: 2019/3/24 15:32
 */
package com.prd.approval.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@CrossOrigin
@Controller
public class DefaultController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }


}

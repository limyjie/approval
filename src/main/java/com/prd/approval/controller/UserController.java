/**
 * Author: lin
 * Date: 2019/3/24 15:39
 */
package com.prd.approval.controller;


import com.prd.approval.entity.User;
import com.prd.approval.service.UserService;
import com.prd.approval.utils.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseUtil<User> login(HttpServletRequest request, @RequestBody User webUser){

        return userService.login(webUser);
    }

    @GetMapping("/getAll")
    public ResponseUtil<List<User>> getAll(){
        return userService.findAllUser();
    }
}

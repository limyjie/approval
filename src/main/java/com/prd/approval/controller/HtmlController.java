/**
 * Author: lin
 * Date: 2019/3/24 15:32
 */
package com.prd.approval.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HtmlController {

    @RequestMapping("/")
    public String index1Page(){
        return "index.html";
    }

    @RequestMapping("/index")
    public String index2Page(){
        return "index.html";
    }

}

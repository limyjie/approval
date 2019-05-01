/**
 * Author: lin
 * Date: 2019/3/24 15:32
 */
package com.prd.approval.controller;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Map;

@CrossOrigin
@Controller
public class DefaultController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @Autowired
    ObjectMapper mapper;

    public void testJson() throws IOException {
        String json = "";
        JsonNode node =  mapper.readTree(json);
        String name = node.get("name").asText();
        int id = node.get("id").asInt();
        System.out.println(name+" "+id);
    }


    @PostMapping("/how/json")
    public void howParseJson2Object(@RequestBody Object requestBody){
        System.out.println(requestBody.getClass().getName());
    }

}

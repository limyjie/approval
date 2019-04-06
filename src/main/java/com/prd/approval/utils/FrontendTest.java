/**
 * Author: lin
 * Date: 2019/3/31 9:43
 */
package com.prd.approval.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Level;

@RestController
public class FrontendTest {

    @RequestMapping("/hello/test")
    public String forTest(HttpServletRequest request, HttpServletResponse response) throws Exception{
        Map<String, String> map = new LinkedHashMap<>();
        //request line
        map.put("method",request.getMethod());
        map.put("protocol",request.getProtocol());
        map.put("URL",request.getRequestURL().toString());
        map.put("queryString",request.getQueryString());
        //request header
        Enumeration<String> headerNames = request.getHeaderNames();

        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            map.put(nextElement,request.getHeader(nextElement));
        }
        //request body
        BufferedReader reader = request.getReader();
        String line;
        StringBuilder stringBuilder = new StringBuilder();
        while((line = reader.readLine())!=null){
            stringBuilder.append(line);
        }
        map.put("requestBody",stringBuilder.toString());
        Object o = JSONObject.toJSON(map);
        return o.toString();
    }
}

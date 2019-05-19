/**
 * Author: lin
 * Date: 2019/2/27 11:04
 */
package com.prd.approval.aspect;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;


@Aspect
@Component
public class HttpAspect {



    @Pointcut("execution(public * com.prd.approval.controller.*.*(..))")
    public void point() {
    }

    /*
    异步输出日志，减少响应时间
     */
    @Before("point()")
    public void beforeExecution() {

       new Thread(new HttpLogTask((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())).start();
    }


}

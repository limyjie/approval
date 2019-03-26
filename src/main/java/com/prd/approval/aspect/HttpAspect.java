/**
 * Author: lin
 * Date: 2019/2/27 11:04
 */
package com.prd.approval.aspect;


import com.alibaba.fastjson.JSON;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Aspect
public class HttpAspect {

    private final static Logger logger = Logger.getLogger(HttpAspect.class.getCanonicalName());

    @Pointcut("execution(public * com.prd.approval.controller.*.*(..))")
    public void point() {
    }

    @Before("point()")
    public void beforeExecution(JoinPoint joinPoint) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        logger.log(Level.WARNING, "------------");
        //获取所有的消息头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        //获取获取的消息头名称，获取对应的值，并输出
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            logger.log(Level.WARNING,nextElement + ":" + request.getHeader(nextElement));
        }

        logger.log(Level.WARNING, "Address: " + request.getRemoteUser());
        logger.log(Level.WARNING, "ContextPath: " + request.getContextPath());
        logger.log(Level.WARNING, "RequestURI: " + request.getRequestURI());
        logger.log(Level.WARNING, "Method: " + request.getMethod());
        logger.log(Level.WARNING, "Parameters: " + JSON.toJSONString(joinPoint.getArgs()));

        logger.log(Level.WARNING, "------------");
        logger.log(Level.WARNING, "");
    }
}

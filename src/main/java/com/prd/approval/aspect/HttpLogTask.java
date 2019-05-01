/**
 * Author: lin
 * Date: 2019/4/27 16:12
 */
package com.prd.approval.aspect;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *<p></p>
 *
 */
public class HttpLogTask implements Runnable {

    private final static Logger logger = Logger.getLogger(HttpAspect.class.getCanonicalName());
    private final ServletRequestAttributes servletRequestAttributes;

    public HttpLogTask(ServletRequestAttributes servletRequestAttributes){
        this.servletRequestAttributes = servletRequestAttributes;
    }

    @Override
    public void run() {

        HttpServletRequest request = servletRequestAttributes.getRequest();
        logger.log(Level.WARNING, "------Start------");
        //获取所有的消息头名称
        Enumeration<String> headerNames = request.getHeaderNames();
        //获取获取的消息头名称，获取对应的值，并输出

        logger.log(Level.WARNING, "Method: " + request.getMethod());
        logger.log(Level.WARNING, "RequestURI: " + request.getRequestURI());
        logger.log(Level.WARNING, "Address: " + request.getRemoteUser());
        logger.log(Level.WARNING, "ContextPath: " + request.getContextPath());
        logger.log(Level.WARNING, "Header: ");
        while (headerNames.hasMoreElements()) {
            String nextElement = headerNames.nextElement();
            logger.log(Level.WARNING, nextElement + ":" + request.getHeader(nextElement));
        }
        logger.log(Level.WARNING, "-------End--------");
        logger.log(Level.WARNING, "");

    }
}

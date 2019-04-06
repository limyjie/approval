package com.prd.approval;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/*
//开发时的启动类
@MapperScan("com.prd.approval.dao")
@SpringBootApplication
public class ApprovalApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApprovalApplication.class, args);
    }

}
*/

//部署时的启动类

@MapperScan("com.prd.approval.dao")
@SpringBootApplication
public class ApprovalApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApprovalApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ApprovalApplication.class, args);
    }

}

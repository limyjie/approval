/**
 * Author: lin
 * Date: 2019/3/5 18:22
 */
package com.prd.approval.entity;


import com.prd.approval.utils.ConstantUtil;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/*
* 登陆人
*
* */
public class User implements Serializable {


    private String id;
    private String userName;
    private String password;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

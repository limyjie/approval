/**
 * Author: lin
 * Date: 2019/5/23 22:22
 */
package com.prd.approval.entity.vo;

/**
 *<p></p>
 *
 */
public class User {
    private String id;
    private String userName;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
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
}

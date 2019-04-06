/**
 * Author: lin
 * Date: 2019/4/3 15:46
 */
package com.prd.approval.entity;



/**
 * <p>审核人/批准人  对应数据库中的 TCCOM001</p>
 */
public class Auditor {
    private String id;
    private String name;
    private String loginName;
    private String duty;
    private String department;

    public Auditor() {
    }

    @Override
    public String toString() {
        return "Auditor{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", loginName='" + loginName + '\'' +
                ", duty='" + duty + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getDuty() {
        return duty;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}

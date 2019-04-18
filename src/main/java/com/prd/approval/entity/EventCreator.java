/**
 * Author: lin
 * Date: 2019/4/15 12:26
 */
package com.prd.approval.entity;

/**
 *<p></p>
 *
 */
public class EventCreator {
    private String id;
    //ap_event.idno
    private String hid;
    //排序号
    private Integer sortNo;
    //创建人名的编码  tccom001.emno
    private String creatorNo;
    //创建人名的名字 tccom001.nama
    private String creatorName;
    //部门信息，暂时闲置
    private String creatorDepartment;
    private String creatorDepartmentName;

    public EventCreator() {
    }

    @Override
    public String toString() {
        return "EventCreator{" +
                "id='" + id + '\'' +
                ", hid='" + hid + '\'' +
                ", sortNo=" + sortNo +
                ", creatorNo='" + creatorNo + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", creatorDepartment='" + creatorDepartment + '\'' +
                ", creatorDepartmentName='" + creatorDepartmentName + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHid() {
        return hid;
    }

    public void setHid(String hid) {
        this.hid = hid;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getCreatorNo() {
        return creatorNo;
    }

    public void setCreatorNo(String creatorNo) {
        this.creatorNo = creatorNo;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorDepartment() {
        return creatorDepartment;
    }

    public void setCreatorDepartment(String creatorDepartment) {
        this.creatorDepartment = creatorDepartment;
    }

    public String getCreatorDepartmentName() {
        return creatorDepartmentName;
    }

    public void setCreatorDepartmentName(String creatorDepartmentName) {
        this.creatorDepartmentName = creatorDepartmentName;
    }
}

/**
 * Author: lin
 * Date: 2019/3/27 13:13
 */
package com.prd.approval.entity;

import java.sql.Date;
import java.sql.Timestamp;

/*
*    ap_step_staff
*
* */
public class StepStaff {

    private String id;
    private String hId;
    private String status;
    private Integer sortNo;
    private String staffNo;
    private String staffName;
    private String staffDepa;
    private String staffDepaName;
    private String apResult;
    private String apComment;
    private Timestamp apDate;

    public StepStaff() {
    }

    @Override
    public String toString() {
        return "StepStaff{" +
                "id='" + id + '\'' +
                ", hId='" + hId + '\'' +
                ", status='" + status + '\'' +
                ", sortNo=" + sortNo +
                ", staffNo='" + staffNo + '\'' +
                ", staffName='" + staffName + '\'' +
                ", staffDepa='" + staffDepa + '\'' +
                ", staffDepaName='" + staffDepaName + '\'' +
                ", apResult='" + apResult + '\'' +
                ", apComment='" + apComment + '\'' +
                ", apDate=" + apDate +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String gethId() {
        return hId;
    }

    public void sethId(String hId) {
        this.hId = hId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getStaffDepa() {
        return staffDepa;
    }

    public void setStaffDepa(String staffDepa) {
        this.staffDepa = staffDepa;
    }

    public String getStaffDepaName() {
        return staffDepaName;
    }

    public void setStaffDepaName(String staffDepaName) {
        this.staffDepaName = staffDepaName;
    }

    public String getApResult() {
        return apResult;
    }

    public void setApResult(String apResult) {
        this.apResult = apResult;
    }

    public String getApComment() {
        return apComment;
    }

    public void setApComment(String apComment) {
        this.apComment = apComment;
    }

    public Timestamp getApDate() {
        return apDate;
    }

    public void setApDate(Timestamp apDate) {
        this.apDate = apDate;
    }
}

/**
 * Author: lin
 * Date: 2019/4/25 14:40
 */
package com.prd.approval.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 *<p>
 *     apply header : apply list = 1:n
 *</p>
 *
 */
public class ApplyHeader {

    private String id;
    private String applyNo;
    private String applyName;
    private String status;
    private String applyType;
    private String projectNo;
    private String applyBy;
    private String applyDepartment;
    private Timestamp planDate;
    private Timestamp createDate;
    private String createBy;
    private Timestamp preIssueDate;
    private String preIssueBy;
    private String comments;
    private Timestamp expirationDate;
    private List<ApplyList> applyListList;

    public ApplyHeader() {
    }

    @Override
    public String toString() {
        return "ApplyHeader{" +
                "id='" + id + '\'' +
                ", applyNo='" + applyNo + '\'' +
                ", applyName='" + applyName + '\'' +
                ", status='" + status + '\'' +
                ", applyType='" + applyType + '\'' +
                ", projectNo='" + projectNo + '\'' +
                ", applyBy='" + applyBy + '\'' +
                ", applyDepartment='" + applyDepartment + '\'' +
                ", planDate=" + planDate +
                ", createDate=" + createDate +
                ", createBy='" + createBy + '\'' +
                ", preIssueDate=" + preIssueDate +
                ", preIssueBy='" + preIssueBy + '\'' +
                ", comments='" + comments + '\'' +
                ", expirationDate=" + expirationDate +
                ", applyListList=" + applyListList +
                '}';
    }

    public List<ApplyList> getApplyListList() {
        return applyListList;
    }

    public void setApplyListList(List<ApplyList> applyListList) {
        this.applyListList = applyListList;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getApplyName() {
        return applyName;
    }

    public void setApplyName(String applyName) {
        this.applyName = applyName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getApplyBy() {
        return applyBy;
    }

    public void setApplyBy(String applyBy) {
        this.applyBy = applyBy;
    }

    public String getApplyDepartment() {
        return applyDepartment;
    }

    public void setApplyDepartment(String applyDepartment) {
        this.applyDepartment = applyDepartment;
    }

    public Timestamp getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Timestamp planDate) {
        this.planDate = planDate;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getPreIssueDate() {
        return preIssueDate;
    }

    public void setPreIssueDate(Timestamp preIssueDate) {
        this.preIssueDate = preIssueDate;
    }

    public String getPreIssueBy() {
        return preIssueBy;
    }

    public void setPreIssueBy(String preIssueBy) {
        this.preIssueBy = preIssueBy;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }
}

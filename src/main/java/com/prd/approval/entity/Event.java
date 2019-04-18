/**
 * Author: lin
 * Date: 2019/4/6 16:59
 */
package com.prd.approval.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 *<p>
 *     ap_event 审批模板 和审批事件 共用该表
 *</p>
 *
 */
public class Event {
    private String id;
    private Integer isModel;
    private String modelId;
    @NotBlank
    private String eventName;//用户输入
    @NotBlank
    private String eventDescription;//用户输入
    @NotNull
    private Integer isActive;//用户输入
    private Integer isUpdate;
    private String status;
    private Integer sortNo;
    private String currentStepId;
    private Integer currentStepSortNo;
    private String billNo;
    private String billCode;
    private String billName;
    private String creatorNo;
    private String creatorName;
    private String createBy;
    private Timestamp createDate;

    public Event() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getIsModel() {
        return isModel;
    }

    public void setIsModel(Integer isModel) {
        this.isModel = isModel;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Integer getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
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

    public String getCurrentStepId() {
        return currentStepId;
    }

    public void setCurrentStepId(String currentStepId) {
        this.currentStepId = currentStepId;
    }

    public Integer getCurrentStepSortNo() {
        return currentStepSortNo;
    }

    public void setCurrentStepSortNo(Integer currentStepSortNo) {
        this.currentStepSortNo = currentStepSortNo;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
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

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id='" + id + '\'' +
                ", isModel=" + isModel +
                ", modelId='" + modelId + '\'' +
                ", eventName='" + eventName + '\'' +
                ", eventDescription='" + eventDescription + '\'' +
                ", isActive=" + isActive +
                ", isUpdate=" + isUpdate +
                ", status='" + status + '\'' +
                ", sortNo=" + sortNo +
                ", currentStepId='" + currentStepId + '\'' +
                ", currentStepSortNo=" + currentStepSortNo +
                ", billNo='" + billNo + '\'' +
                ", billCode='" + billCode + '\'' +
                ", billName='" + billName + '\'' +
                ", creatorNo='" + creatorNo + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}

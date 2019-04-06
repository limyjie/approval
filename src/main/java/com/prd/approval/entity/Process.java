/**
 * Author: lin
 * Date: 2019/3/25 17:40
 */
package com.prd.approval.entity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 审批阶段
 */
public class Process implements Serializable{

    /*阶段代码，对应数据库中的IDNO字段 用IDNOUtil自生成*/
    private String Id;
    /*阶段名*/
   // @NotBlank(message = "阶段名不能为空")
    private String stepName;
    /*阶段描述*/
   // @NotBlank(message = "阶段描述不能为空")
    private String stepDescription;
    /*需要通过的次数*/
//    @Min(value = 1 ,message = "最小值为1")
    private Integer timesCount;
    /*创建人*/
   // @NotNull(message = "创建人不能为空")
    private String createBy;
    /*创建日期*/
    private Timestamp createDate;
    /**/
    private String eventId;
    private Integer sortNo;
    private Integer stepCount;
    private String sourceStepId;
    private String stepCode;
    private Integer stepType;

    private String status;
    private Integer timesRemain;


    public Process(){

    }

    @Override
    public String toString() {
        return "Process{" +
                "Id='" + Id + '\'' +
                ", eventId='" + eventId + '\'' +
                ", sortNo=" + sortNo +
                ", stepCount=" + stepCount +
                ", sourceStepId='" + sourceStepId + '\'' +
                ", stepCode='" + stepCode + '\'' +
                ", stepType=" + stepType +
                ", stepName='" + stepName + '\'' +
                ", stepDescription='" + stepDescription + '\'' +
                ", timesCount=" + timesCount +
                ", status='" + status + '\'' +
                ", timesRemain=" + timesRemain +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                '}';
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public Integer getStepCount() {
        return stepCount;
    }

    public void setStepCount(Integer stepCount) {
        this.stepCount = stepCount;
    }

    public String getSourceStepId() {
        return sourceStepId;
    }

    public void setSourceStepId(String sourceStepId) {
        this.sourceStepId = sourceStepId;
    }

    public String getStepCode() {
        return stepCode;
    }

    public void setStepCode(String stepCode) {
        this.stepCode = stepCode;
    }

    public Integer getStepType() {
        return stepType;
    }

    public void setStepType(Integer stepType) {
        this.stepType = stepType;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public String getStepDescription() {
        return stepDescription;
    }

    public void setStepDescription(String stepDescription) {
        this.stepDescription = stepDescription;
    }

    public Integer getTimesCount() {
        return timesCount;
    }

    public void setTimesCount(Integer timesCount) {
        this.timesCount = timesCount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTimesRemain() {
        return timesRemain;
    }

    public void setTimesRemain(Integer timesRemain) {
        this.timesRemain = timesRemain;
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
}

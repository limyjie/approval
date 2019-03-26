/**
 * Author: lin
 * Date: 2019/3/25 17:40
 */
package com.prd.approval.entity;

import oracle.sql.TIMESTAMP;

import java.io.Serializable;
import java.sql.Timestamp;

public class Process implements Serializable{
    private String Id;
    private String eventId;
    private Integer sortNo;
    private Integer stepCount;
    private String sourceStepId;
    private String stepCode;
    private Integer stepType;
    private String stepName;
    private String stepDescription;
    private Integer timesCount;
    private String status;
    private Integer timesRemain;
    private String createBy;
    private Timestamp createDate;
}

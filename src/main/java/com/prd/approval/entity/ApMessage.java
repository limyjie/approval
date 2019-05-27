/**
 * Author: lin
 * Date: 2019/5/12 14:52
 */
package com.prd.approval.entity;

/**
 *<p></p>
 *
 */
public class ApMessage {
    private String id;
    private String eventId;
    private String staffNo;
    private String messageId;
    private String stepId;
    private String staffId;

    public ApMessage() {
    }

    public ApMessage(String id, String eventId, String staffNo, String messageId, String stepId, String staffId) {
        this.id = id;
        this.eventId = eventId;
        this.staffNo = staffNo;
        this.messageId = messageId;
        this.stepId = stepId;
        this.staffId = staffId;
    }

    @Override
    public String toString() {
        return "ApMessage{" +
                "id='" + id + '\'' +
                ", eventId='" + eventId + '\'' +
                ", staffNo='" + staffNo + '\'' +
                ", messageId='" + messageId + '\'' +
                ", stepId='" + stepId + '\'' +
                ", staffId='" + staffId + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getStaffNo() {
        return staffNo;
    }

    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getStepId() {
        return stepId;
    }

    public void setStepId(String stepId) {
        this.stepId = stepId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }
}

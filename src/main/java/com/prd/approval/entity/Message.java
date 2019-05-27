/**
 * Author: lin
 * Date: 2019/4/21 11:22
 */
package com.prd.approval.entity;

import oracle.sql.TIMESTAMP;

import java.sql.Timestamp;

/**
 *<p>
 *     MESSAGE 表
 *
 *</p>
 *
 */
public class Message {
    private String id;
    private String fromUser;
    private String toUser;
    private Integer sera;
    private String subject;
    private String content;
    private Timestamp sendTime;
    private String haveRead;
    private String haveAlert;
    private String haveDone;
    private String messageType;
    /*以下属性数据库中不存在，但仍需用到*/
    private String toUserName;
    private String fromUserName;

    public Message() {
    }

    public Message(String id, String fromUser, String toUser, Integer sera, String subject, String content, Timestamp sendTime, String haveRead, String haveAlert, String haveDone, String messageType) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.sera = sera;
        this.subject = subject;
        this.content = content;
        this.sendTime = sendTime;
        this.haveRead = haveRead;
        this.haveAlert = haveAlert;
        this.haveDone = haveDone;
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", fromUser='" + fromUser + '\'' +
                ", toUser='" + toUser + '\'' +
                ", sera=" + sera +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", sendTime=" + sendTime +
                ", haveRead='" + haveRead + '\'' +
                ", haveAlert='" + haveAlert + '\'' +
                ", haveDone='" + haveDone + '\'' +
                ", messageType='" + messageType + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                '}';
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public Integer getSera() {
        return sera;
    }

    public void setSera(Integer sera) {
        this.sera = sera;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getSendTime() {
        return sendTime;
    }

    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    public String getHaveRead() {
        return haveRead;
    }

    public void setHaveRead(String haveRead) {
        this.haveRead = haveRead;
    }

    public String getHaveAlert() {
        return haveAlert;
    }

    public void setHaveAlert(String haveAlert) {
        this.haveAlert = haveAlert;
    }

    public String getHaveDone() {
        return haveDone;
    }

    public void setHaveDone(String haveDone) {
        this.haveDone = haveDone;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}

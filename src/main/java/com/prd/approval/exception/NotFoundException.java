/**
 * Author: lin
 * Date: 2019/3/24 16:13
 */
package com.prd.approval.exception;

public class NotFoundException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private Integer status;
    private String msg;

    public NotFoundException() {
    }

    public NotFoundException(Integer status, String msg) {
        super();
        this.status = status;
        this.msg = msg;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

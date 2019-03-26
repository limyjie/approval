/**
 * Author: lin
 * Date: 2019/3/9 15:38
 */
package com.prd.approval.utils;

public class ResponseUtil<T> {
    private  Integer status;
    private  String msg;
    private  T data;

    public ResponseUtil(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ResponseUtil(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
        this.data = null;
    }

    public static ResponseUtil send(Integer status,String msg){
        return new ResponseUtil(status,msg);
    }

    public static ResponseUtil send(Integer status,String msg,Object data){
        return new ResponseUtil(status,msg,data);
    }

    @Override
    public String toString() {
        return "ResponseUtil{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

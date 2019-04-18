/**
 * Author: lin
 * Date: 2019/4/15 15:20
 */
package com.prd.approval.entity;

/**
 *<p></p>
 *
 */
public class EventBill {
    private String id;
    private String hid;
    private Integer isModel;
    private String billCode;
    private String billName;
    private Integer sortNo;

    public EventBill() {
    }

    @Override
    public String toString() {
        return "EventBill{" +
                "id='" + id + '\'' +
                ", hid='" + hid + '\'' +
                ", isModel=" + isModel +
                ", billCode='" + billCode + '\'' +
                ", billName='" + billName + '\'' +
                ", sortNo=" + sortNo +
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

    public Integer getIsModel() {
        return isModel;
    }

    public void setIsModel(Integer isModel) {
        this.isModel = isModel;
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

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }
}

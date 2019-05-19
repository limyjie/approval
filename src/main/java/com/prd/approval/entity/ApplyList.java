/**
 * Author: lin
 * Date: 2019/4/25 14:45
 */
package com.prd.approval.entity;

import java.sql.Timestamp;

/**
 *<p></p>
 *
 */
public class ApplyList {

    private String id;
    private String hid;
    private String applyNo;
    private String listType;
    private String listStatus;

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    private Integer sortNo;
    private String  purchaseStaff;
    private Timestamp planDate;
    private String projectNo;
    private String item;
    private String itemCCGG;
    private Double quantity;
    private String unit;
    private String unitType;
    private Double invQuantity;
    private String invUnit;
    private Double conversionRate;
    private String whID;
    private Double copr;
    private Double amount;
    private String taxNo;
    private Double taxRate;
    private Double taxAmount;
    private Double amountWithTax;
    private String suNo;
    private String isTransferNotIncluded;
    private String financeIssueStatus;
    private String reasonForReject;
    private String issueBy;
    private Timestamp issueDate;
    private String submitBy;
    private Timestamp submitDate;
    private String createBy;
    private Timestamp createDate;
    private String comments;
    private String attribute1;
    private String attribute2;
    private String attribute3;
    private String kltc;
    /*以下属性在数据库中不存在，但仍需用到*/
    private String projectName;
    /*库房名*/
    private String warehouseName;
    //物料名
    private String itemName;
    private String whCode;
    //单位名
    private String unitName;

    private String creatorName;

    private String purchaseStaffName;

    public ApplyList() {
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getPurchaseStaffName() {
        return purchaseStaffName;
    }

    public void setPurchaseStaffName(String purchaseStaffName) {
        this.purchaseStaffName = purchaseStaffName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getWarehouseName() {
        return warehouseName;
    }

    public void setWarehouseName(String warehouseName) {
        this.warehouseName = warehouseName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getWhCode() {
        return whCode;
    }

    public void setWhCode(String whCode) {
        this.whCode = whCode;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
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

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public Integer getSortNo() {
        return sortNo;
    }

    public void setSortNo(Integer sortNo) {
        this.sortNo = sortNo;
    }

    public String getPurchaseStaff() {
        return purchaseStaff;
    }

    public void setPurchaseStaff(String purchaseStaff) {
        this.purchaseStaff = purchaseStaff;
    }

    public Timestamp getPlanDate() {
        return planDate;
    }

    public void setPlanDate(Timestamp planDate) {
        this.planDate = planDate;
    }

    public String getProjectNo() {
        return projectNo;
    }

    public void setProjectNo(String projectNo) {
        this.projectNo = projectNo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public String getItemCCGG() {
        return itemCCGG;
    }

    public void setItemCCGG(String itemCCGG) {
        this.itemCCGG = itemCCGG;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitType() {
        return unitType;
    }

    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }

    public Double getInvQuantity() {
        return invQuantity;
    }

    public void setInvQuantity(Double invQuantity) {
        this.invQuantity = invQuantity;
    }

    public String getInvUnit() {
        return invUnit;
    }

    public void setInvUnit(String invUnit) {
        this.invUnit = invUnit;
    }

    public Double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(Double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public String getWhID() {
        return whID;
    }

    public void setWhID(String whID) {
        this.whID = whID;
    }

    public Double getCopr() {
        return copr;
    }

    public void setCopr(Double copr) {
        this.copr = copr;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getTaxNo() {
        return taxNo;
    }

    public void setTaxNo(String taxNo) {
        this.taxNo = taxNo;
    }

    public Double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(Double taxRate) {
        this.taxRate = taxRate;
    }

    public Double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public Double getAmountWithTax() {
        return amountWithTax;
    }

    public void setAmountWithTax(Double amountWithTax) {
        this.amountWithTax = amountWithTax;
    }

    public String getSuNo() {
        return suNo;
    }

    public void setSuNo(String suNo) {
        this.suNo = suNo;
    }

    public String getIsTransferNotIncluded() {
        return isTransferNotIncluded;
    }

    public void setIsTransferNotIncluded(String isTransferNotIncluded) {
        this.isTransferNotIncluded = isTransferNotIncluded;
    }

    public String getFinanceIssueStatus() {
        return financeIssueStatus;
    }

    public void setFinanceIssueStatus(String financeIssueStatus) {
        this.financeIssueStatus = financeIssueStatus;
    }

    public String getReasonForReject() {
        return reasonForReject;
    }

    public void setReasonForReject(String reasonForReject) {
        this.reasonForReject = reasonForReject;
    }

    public String getIssueBy() {
        return issueBy;
    }

    public void setIssueBy(String issueBy) {
        this.issueBy = issueBy;
    }

    public Timestamp getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Timestamp issueDate) {
        this.issueDate = issueDate;
    }

    public String getSubmitBy() {
        return submitBy;
    }

    public void setSubmitBy(String submitBy) {
        this.submitBy = submitBy;
    }

    public Timestamp getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Timestamp submitDate) {
        this.submitDate = submitDate;
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

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAttribute1() {
        return attribute1;
    }

    public void setAttribute1(String attribute1) {
        this.attribute1 = attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public void setAttribute2(String attribute2) {
        this.attribute2 = attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }

    public void setAttribute3(String attribute3) {
        this.attribute3 = attribute3;
    }

    public String getKltc() {
        return kltc;
    }

    public void setKltc(String kltc) {
        this.kltc = kltc;
    }

    @Override
    public String toString() {
        return "ApplyList{" +
                "id='" + id + '\'' +
                ", hid='" + hid + '\'' +
                ", applyNo='" + applyNo + '\'' +
                ", listType='" + listType + '\'' +
                ", listStatus='" + listStatus + '\'' +
                ", sortNo=" + sortNo +
                ", purchaseStaff='" + purchaseStaff + '\'' +
                ", planDate=" + planDate +
                ", projectNo='" + projectNo + '\'' +
                ", item='" + item + '\'' +
                ", itemCCGG='" + itemCCGG + '\'' +
                ", quantity=" + quantity +
                ", unit='" + unit + '\'' +
                ", unitType='" + unitType + '\'' +
                ", invQuantity=" + invQuantity +
                ", invUnit='" + invUnit + '\'' +
                ", conversionRate=" + conversionRate +
                ", whID='" + whID + '\'' +
                ", copr=" + copr +
                ", amount=" + amount +
                ", taxNo='" + taxNo + '\'' +
                ", taxRate=" + taxRate +
                ", taxAmount=" + taxAmount +
                ", amountWithTax=" + amountWithTax +
                ", suNo='" + suNo + '\'' +
                ", isTransferNotIncluded='" + isTransferNotIncluded + '\'' +
                ", financeIssueStatus='" + financeIssueStatus + '\'' +
                ", reasonForReject='" + reasonForReject + '\'' +
                ", issueBy='" + issueBy + '\'' +
                ", issueDate=" + issueDate +
                ", submitBy='" + submitBy + '\'' +
                ", submitDate=" + submitDate +
                ", createBy='" + createBy + '\'' +
                ", createDate=" + createDate +
                ", comments='" + comments + '\'' +
                ", attribute1='" + attribute1 + '\'' +
                ", attribute2='" + attribute2 + '\'' +
                ", attribute3='" + attribute3 + '\'' +
                ", kltc='" + kltc + '\'' +
                ", projectName='" + projectName + '\'' +
                ", warehouseName='" + warehouseName + '\'' +
                ", itemName='" + itemName + '\'' +
                ", whCode='" + whCode + '\'' +
                ", unitName='" + unitName + '\'' +
                ", creatorName='" + creatorName + '\'' +
                ", purchaseStaffName='" + purchaseStaffName + '\'' +
                '}';
    }
}

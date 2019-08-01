package edu.sjtu.naocemis.entity;




import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author alan_chen
 * @date 2019/7/29
 */
@Entity(name = "consumable")
public class Consumable implements Serializable {
    @Id
    private String id;
    private String applyer;
    private String applyerAccount;
    private String dept; // 部门
    private Date applyDate; // 申请日期
    private Date demandDate; // 所需日期
    private String applyReason; // 申请理由
    private Date noticeReceiveDate; // 通知领取时间
    private String confirmStatus; // 审批状态 Y N
    private Date receiveDate; // 领取时间
    @Column(name = "description")
    private String desc; // 备注
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;
    private String flowId; // 流程号
    private String receiveStatus; // 领取状态 Y N

    @Transient
    private List<ConsumableProduct> products;


    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Date getDemandDate() {
        return demandDate;
    }

    public void setDemandDate(Date demandDate) {
        this.demandDate = demandDate;
    }

    public String getApplyReason() {
        return applyReason;
    }

    public void setApplyReason(String applyReason) {
        this.applyReason = applyReason;
    }

    public Date getNoticeReceiveDate() {
        return noticeReceiveDate;
    }

    public void setNoticeReceiveDate(Date noticeReceiveDate) {
        this.noticeReceiveDate = noticeReceiveDate;
    }

    public String getConfirmStatus() {
        return confirmStatus;
    }

    public void setConfirmStatus(String confirmStatus) {
        this.confirmStatus = confirmStatus;
    }

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<ConsumableProduct> getProducts() {
        return products;
    }

    public void setProducts(List<ConsumableProduct> products) {
        this.products = products;
    }

    public String getApplyerAccount() {
        return applyerAccount;
    }

    public void setApplyerAccount(String applyerAccount) {
        this.applyerAccount = applyerAccount;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public String getReceiveStatus() {
        return receiveStatus;
    }

    public void setReceiveStatus(String receiveStatus) {
        this.receiveStatus = receiveStatus;
    }
}

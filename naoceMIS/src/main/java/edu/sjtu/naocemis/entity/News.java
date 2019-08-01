package edu.sjtu.naocemis.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author hongzexuan
 * @date 2019/7/30
 */
@Entity(name = "news")//file createDate updateDate AgreeStatus
public class News implements Serializable {
    @Id
    private String id;
    private String applyer;
    private String applyerAccount;
    private String author;
    private String category;
    private String deptOne;
    private String dept;
    private Date newsDate;
    private String title;
    private String secret;
    private String deptLeader;
    private Date deptLeaderDate;
    private String deptLeaderSug;
    private String secretary;
    private Date secretaryDate;
    private String secretarySug;
    private String colLeader;
    private Date colLeaderDate;
    private String colLeaderSug;
    private String deptLeaderCheckStatus;
    private String secretaryCheckStatus;
    private String colLeaderCheckStatus;
    private String secretaryReleaseStatus;
    private String flowId;
    private String file;
    private Date createTime;
    private Date updateTime;

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

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    public String getDeptLeaderCheckStatus() {
        return deptLeaderCheckStatus;
    }

    public void setDeptLeaderCheckStatus(String deptLeaderCheckStatus) {
        this.deptLeaderCheckStatus = deptLeaderCheckStatus;
    }

    public String getSecretaryCheckStatus() {
        return secretaryCheckStatus;
    }

    public void setSecretaryCheckStatus(String secretaryCheckStatus) {
        this.secretaryCheckStatus = secretaryCheckStatus;
    }

    public String getColLeaderCheckStatus() {
        return colLeaderCheckStatus;
    }

    public void setColLeaderCheckStatus(String colLeaderCheckStatus) {
        this.colLeaderCheckStatus = colLeaderCheckStatus;
    }

    public String getSecretaryReleaseStatus() {
        return secretaryReleaseStatus;
    }

    public void setSecretaryReleaseStatus(String secretaryReleaseStatus) {
        this.secretaryReleaseStatus = secretaryReleaseStatus;
    }




    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
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

    public String getApplyerAccount() {
        return applyerAccount;
    }

    public void setApplyerAccount(String applyerAccount) {
        this.applyerAccount = applyerAccount;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeptOne() {
        return deptOne;
    }

    public void setDeptOne(String deptOne) {
        this.deptOne = deptOne;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Date getNewsDate() {
        return newsDate;
    }

    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getDeptLeader() {
        return deptLeader;
    }

    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }

    public Date getDeptLeaderDate() {
        return deptLeaderDate;
    }

    public void setDeptLeaderDate(Date deptLeaderDate) {
        this.deptLeaderDate = deptLeaderDate;
    }

    public String getDeptLeaderSug() {
        return deptLeaderSug;
    }

    public void setDeptLeaderSug(String deptLeaderSug) {
        this.deptLeaderSug = deptLeaderSug;
    }

    public String getSecretary() {
        return secretary;
    }

    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }

    public Date getSecretaryDate() {
        return secretaryDate;
    }

    public void setSecretaryDate(Date secretaryDate) {
        this.secretaryDate = secretaryDate;
    }

    public String getSecretarySug() {
        return secretarySug;
    }

    public void setSecretarySug(String secretarySug) {
        this.secretarySug = secretarySug;
    }

    public String getColLeader() {
        return colLeader;
    }

    public void setColLeader(String colLeader) {
        this.colLeader = colLeader;
    }

    public Date getColLeaderDate() {
        return colLeaderDate;
    }

    public void setColLeaderDate(Date colLeaderDate) {
        this.colLeaderDate = colLeaderDate;
    }

    public String getColLeaderSug() {
        return colLeaderSug;
    }

    public void setColLeaderSug(String colLeaderSug) {
        this.colLeaderSug = colLeaderSug;
    }



}
package edu.sjtu.naocemis.flow.model;

import edu.sjtu.common.em.AgreeStatusEm;
import edu.sjtu.common.util.UUIDGenerator;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusEntity;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusEvent;
import edu.sjtu.infoplus.applicationToolkit.model.CodeItem;
import edu.sjtu.naocemis.entity.News;

import java.util.Date;
import java.util.List;

public class NewsForm extends InfoPlusEntity {

    private String applyer;
    private String author;
    private CodeItem category;
    private CodeItem deptOne;
    private CodeItem dept;
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
    private String file;


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }



    public String getApplyer() {
        return this.applyer;
    }
    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public String getAuthor() {
        return this.author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }


    public CodeItem getCategory() {
        return this.category;
    }
    public void setCategory(CodeItem category) {
        this.category = category;
    }


    public String getColLeader() {
        return this.colLeader;
    }
    public void setColLeader(String colLeader) {
        this.colLeader = colLeader;
    }


    public Date getColLeaderDate() {
        return this.colLeaderDate;
    }
    public void setColLeaderDate(Date colLeaderDate) {
        this.colLeaderDate = colLeaderDate;
    }


    public String getColLeaderSug() {
        return this.colLeaderSug;
    }
    public void setColLeaderSug(String colLeaderSug) {
        this.colLeaderSug = colLeaderSug;
    }


    public CodeItem getDept() {
        return this.dept;
    }
    public void setDept(CodeItem dept) {
        this.dept = dept;
    }


    public String getDeptLeader() {
        return this.deptLeader;
    }
    public void setDeptLeader(String deptLeader) {
        this.deptLeader = deptLeader;
    }


    public Date getDeptLeaderDate() {
        return this.deptLeaderDate;
    }
    public void setDeptLeaderDate(Date deptLeaderDate) {
        this.deptLeaderDate = deptLeaderDate;
    }


    public String getDeptLeaderSug() {
        return this.deptLeaderSug;
    }
    public void setDeptLeaderSug(String deptLeaderSug) {
        this.deptLeaderSug = deptLeaderSug;
    }


    public CodeItem getDeptOne() {
        return this.deptOne;
    }
    public void setDeptOne(CodeItem deptOne) {
        this.deptOne = deptOne;
    }


    public Date getNewsDate() {
        return this.newsDate;
    }
    public void setNewsDate(Date newsDate) {
        this.newsDate = newsDate;
    }


    public String getSecret() {
        return this.secret;
    }
    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getSecretary() {
        return this.secretary;
    }
    public void setSecretary(String secretary) {
        this.secretary = secretary;
    }


    public Date getSecretaryDate() {
        return this.secretaryDate;
    }
    public void setSecretaryDate(Date secretaryDate) {
        this.secretaryDate = secretaryDate;
    }

    public String getSecretarySug() {
        return this.secretarySug;
    }
    public void setSecretarySug(String secretarySug) {
        this.secretarySug = secretarySug;
    }


    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }



    public News setData(InfoPlusEvent e){
        News obj = new News();
        obj.setId(UUIDGenerator.getUUID());
        obj.setApplyer(this.getApplyer());
        obj.setApplyerAccount((String)e.getFormData().get("_VAR_ACTION_ACCOUNT"));
        obj.setTitle(this.getTitle());
        obj.setAuthor(this.getAuthor());
        obj.setCategory(this.getCategory().getCodeName());
        obj.setNewsDate(this.getNewsDate());
        obj.setSecret(this.getSecret());
        obj.setDeptOne(this.getDeptOne().getCodeName());
        obj.setDept(this.getDept().getCodeName());
        long flowId = e.getStep().getInstance().getEntryId();
        obj.setFlowId(flowId+"");
        obj.setFile(this.getFile());
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
        //审核状态
        obj.setDeptLeaderCheckStatus(AgreeStatusEm.DIS_AGREE.getCode());
        obj.setSecretaryCheckStatus(AgreeStatusEm.DIS_AGREE.getCode());
        obj.setColLeaderCheckStatus(AgreeStatusEm.DIS_AGREE.getCode());

        //发布状态
        obj.setSecretaryReleaseStatus(AgreeStatusEm.DIS_Release.getCode());



        return obj;
    }

}

package edu.sjtu.naocemis.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * @author alan_chen
 * @date 2019/7/30
 */
@Entity(name = "consumable_product")
public class ConsumableProduct {

    @Id
    private String id;
    private String comsumableId;
    private String materialName;
    private double materialAmount;
    private String materialFormat;
    @JsonIgnore
    private Date createTime;
    @JsonIgnore
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getComsumableId() {
        return comsumableId;
    }

    public void setComsumableId(String comsumableId) {
        this.comsumableId = comsumableId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public double getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(double materialAmount) {
        this.materialAmount = materialAmount;
    }

    public String getMaterialFormat() {
        return materialFormat;
    }

    public void setMaterialFormat(String materialFormat) {
        this.materialFormat = materialFormat;
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
}

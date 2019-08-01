package edu.sjtu.naocemis.flow.model;

import edu.sjtu.infoplus.applicationToolkit.InfoPlusEntity;

/**
 * @author alan_chen
 * @date 2019/7/30
 */
public class ConsumProducts extends InfoPlusEntity {

    private String productNo;
    private String materialName;
    private Integer materialAmount;
    private String materialFormat;

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public Integer getMaterialAmount() {
        return materialAmount;
    }

    public void setMaterialAmount(Integer materialAmount) {
        this.materialAmount = materialAmount;
    }

    public String getMaterialFormat() {
        return materialFormat;
    }

    public void setMaterialFormat(String materialFormat) {
        this.materialFormat = materialFormat;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
}

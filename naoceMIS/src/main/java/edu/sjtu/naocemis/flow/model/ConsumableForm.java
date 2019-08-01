package edu.sjtu.naocemis.flow.model;

import edu.sjtu.common.em.AgreeStatusEm;
import edu.sjtu.common.em.ReceiveStatusEm;
import edu.sjtu.common.util.UUIDGenerator;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusEntity;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusEvent;
import edu.sjtu.infoplus.applicationToolkit.model.CodeItem;
import edu.sjtu.naocemis.entity.Consumable;
import edu.sjtu.naocemis.entity.ConsumableProduct;
import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author alan_chen
 * @date 2019/7/29
 */
public class ConsumableForm extends InfoPlusEntity {
    private String applyer;
    private CodeItem dept;
    private Date applyDate;
    private Date demandDate; // 所需日期
    private String applyReason;
    private Date noticeReceiveDate;
    private String confirmStatus;
    private Date receiveDate;
    private String desc;
    private List<ConsumProducts> products;

    public String getApplyer() {
        return applyer;
    }

    public void setApplyer(String applyer) {
        this.applyer = applyer;
    }

    public CodeItem getDept() {
        return dept;
    }

    public void setDept(CodeItem dept) {
        this.dept = dept;
    }

    public Date getApplyDate() {
        return applyDate;
    }

    public void setApplyDate(Date applyDate) {
        this.applyDate = applyDate;
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

    public List<ConsumProducts> getProducts() {
        return products;
    }

    public void setProducts(List<ConsumProducts> products) {
        this.products = products;
    }

    public Consumable setData(InfoPlusEvent event) {
        Consumable obj = new Consumable();
        obj.setId(UUIDGenerator.getUUID());
        obj.setApplyer(this.getApplyer());
        obj.setApplyerAccount((String) event.getFormData().get("_VAR_ACTION_ACCOUNT"));
        obj.setDept(this.getDept().getCodeId());
        obj.setApplyDate(this.getApplyDate());
        obj.setDemandDate(this.getDemandDate());
        obj.setApplyReason(this.getApplyReason());
        obj.setCreateTime(new Date());
        obj.setUpdateTime(new Date());
        long flowId = event.getStep().getInstance().getEntryId();
        obj.setFlowId(flowId+"");
        obj.setConfirmStatus(AgreeStatusEm.DIS_AGREE.getCode());
        obj.setReceiveStatus(ReceiveStatusEm.NO.getCode());

        ConsumableProduct product = null;
        List<ConsumableProduct> list = new ArrayList<>();

        List<ConsumProducts> products = this.getProducts();

        if (!CollectionUtils.isEmpty(this.getProducts())) {
            for (ConsumProducts temp : products) {
                product = new ConsumableProduct();
                String uuid = UUIDGenerator.getUUID();
                product.setId(uuid);
                temp.setProductNo(uuid);
                product.setComsumableId(obj.getId());
                product.setMaterialName(temp.getMaterialName().trim());
                product.setMaterialAmount(temp.getMaterialAmount());
                product.setMaterialFormat(temp.getMaterialFormat());
                product.setCreateTime(new Date());
                product.setUpdateTime(new Date());
                list.add(product);
            }
        }

        obj.setProducts(list);

        return obj;
    }
}

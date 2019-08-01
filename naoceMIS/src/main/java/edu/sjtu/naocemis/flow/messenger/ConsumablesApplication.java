package edu.sjtu.naocemis.flow.messenger;

import edu.sjtu.common.em.AgreeStatusEm;
import edu.sjtu.common.em.ReceiveStatusEm;
import edu.sjtu.infoplus.applicationToolkit.AbstractMessenger;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusEvent;
import edu.sjtu.infoplus.applicationToolkit.InfoPlusResponse;
import edu.sjtu.naocemis.entity.Consumable;
import edu.sjtu.naocemis.entity.ConsumableProduct;
import edu.sjtu.naocemis.flow.model.ConsumableForm;
import edu.sjtu.naocemis.service.ConsumableProductService;
import edu.sjtu.naocemis.service.ConsumableService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

/**
 * 船建学院办公易耗品申请
 * @author alan_chen
 * @date 2019/7/29
 */
public class ConsumablesApplication extends AbstractMessenger {

    @Autowired
    ConsumableService consumableService;
    @Autowired
    ConsumableProductService consumableProductService;


    // 终止流程操作
    @Override
    public InfoPlusResponse onInstanceKilled(InfoPlusEvent event) {
        long flowId = event.getStep().getInstance().getEntryId();
        consumableService.deleteByFlowId(flowId + "");
        return null;
    }


    // 申请doing
    public InfoPlusResponse onStepApplyActionNextDoing(InfoPlusEvent event) {
        ConsumableForm form = event.toBean(ConsumableForm.class);
        Consumable consumable = form.setData(event);
        consumableService.saveConsumable(consumable);
        return new InfoPlusResponse(event, form);
    }

    // 修改申请doing
    public InfoPlusResponse onStepModifyActionNextDoing(InfoPlusEvent event) {
        ConsumableForm form = event.toBean(ConsumableForm.class);
        long flowId = event.getStep().getInstance().getEntryId();
        // 如果已存在，则删除旧的数据
        consumableService.deleteByFlowId(flowId + "");
        // 新增
        Consumable consumable = form.setData(event);
        consumableService.saveConsumable(consumable);

        return new InfoPlusResponse(event, form);
    }


    // 审批doing
    public InfoPlusResponse onStepAgreeActionNextDoing(InfoPlusEvent event) {
        ConsumableForm form = event.toBean(ConsumableForm.class);
        long flowId = event.getStep().getInstance().getEntryId();
        // 审核通过，修改审核状态
        Consumable consumable = consumableService.getByFlowId(flowId + "");
        consumable.setNoticeReceiveDate(form.getNoticeReceiveDate());
        consumable.setConfirmStatus(AgreeStatusEm.AGREE.getCode());
        consumable.setUpdateTime(new Date());
        consumable.setDesc(form.getDesc());
        consumableService.update(consumable);
        return new InfoPlusResponse(event, form);
    }

    // 撤回doing
    public InfoPlusResponse onStepDisagreeActionNextDoing(InfoPlusEvent event) {
        long flowId = event.getStep().getInstance().getEntryId();
        // 审核通过，修改审核状态
        Consumable consumable = consumableService.getByFlowId(flowId + "");
        consumable.setConfirmStatus(AgreeStatusEm.DIS_AGREE.getCode());
        consumable.setUpdateTime(new Date());
        consumableService.update(consumable);
        return null;
    }

    // 领取登记doing
    public InfoPlusResponse onStepReceiveActionNextDoing(InfoPlusEvent event) {
        ConsumableForm form = event.toBean(ConsumableForm.class);
        form.setConfirmStatus("确认已领取");
        long flowId = event.getStep().getInstance().getEntryId();

        Consumable consumable = consumableService.getByFlowId(flowId + "");
        consumable.setReceiveDate(form.getReceiveDate());
        consumable.setReceiveStatus(ReceiveStatusEm.YES.getCode());
        consumable.setUpdateTime(new Date());
        consumableService.update(consumable);
        return new InfoPlusResponse(event, form);
    }

}

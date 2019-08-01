package edu.sjtu.naocemis.service.impl;

import edu.sjtu.naocemis.dao.ConsumableDao;
import edu.sjtu.naocemis.dao.ConsumableProductDao;
import edu.sjtu.naocemis.entity.Consumable;
import edu.sjtu.naocemis.service.ConsumableProductService;
import edu.sjtu.naocemis.service.ConsumableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * @author alan_chen
 * @date 2019/7/30
 */
@Service
@Transactional
public class ConsumableProductServiceImpl implements ConsumableProductService {

    private static final Logger log = LoggerFactory.getLogger(ConsumableProductServiceImpl.class);

    @Autowired
    ConsumableProductDao consumableProductDao;
    @Autowired
    ConsumableService consumableService;
    @Autowired
    ConsumableDao consumableDao;



    @Override
    public Consumable getByFlowId(String flowId) {
        Consumable consumable = consumableService.getByFlowId(flowId);
        if (consumable != null) {
            consumable.setProducts( consumableProductDao.getByComsumableId(consumable.getId()));
        }

        return consumable;
    }


}

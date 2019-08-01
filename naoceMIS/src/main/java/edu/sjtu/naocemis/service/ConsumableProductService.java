package edu.sjtu.naocemis.service;

import edu.sjtu.naocemis.entity.Consumable;


public interface ConsumableProductService {
    Consumable getByFlowId(String flowId);

}

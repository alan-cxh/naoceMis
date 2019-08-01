package edu.sjtu.naocemis.dao;

import edu.sjtu.naocemis.entity.Consumable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumableDao extends GenericDao<Consumable, String> {

    List<Consumable> getByFlowId(String flowId);

}

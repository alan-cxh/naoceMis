package edu.sjtu.naocemis.service;

import edu.sjtu.naocemis.dto.ConsumableQueryDto;
import edu.sjtu.naocemis.dto.UserDto;
import edu.sjtu.naocemis.entity.Consumable;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ConsumableService {
    List<Consumable> getById(String id);

    void saveConsumable(Consumable consumable);

    Consumable getByFlowId(String flowId);

    void update(Consumable consumable);

    boolean deleteByFlowId(String s);

    Page<Consumable> getList(ConsumableQueryDto dto);

    List getApplyerList(UserDto userDto);
}

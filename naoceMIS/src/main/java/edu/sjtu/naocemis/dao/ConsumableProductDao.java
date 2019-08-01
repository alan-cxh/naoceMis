package edu.sjtu.naocemis.dao;

import edu.sjtu.naocemis.entity.ConsumableProduct;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ConsumableProductDao extends GenericDao<ConsumableProduct, String> {

    List<ConsumableProduct> getByComsumableId(String comsumableId);

    @Modifying
    @Query(value = "delete from consumable_product where comsumable_id=?1 ",nativeQuery = true)
    int deleteFromComsumableId(String comsumableId);

}

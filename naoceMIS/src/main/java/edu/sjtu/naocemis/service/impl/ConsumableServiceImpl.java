package edu.sjtu.naocemis.service.impl;

import edu.sjtu.common.util.DateUtil;
import edu.sjtu.naocemis.dao.ConsumableDao;
import edu.sjtu.naocemis.dao.ConsumableProductDao;
import edu.sjtu.naocemis.dto.ConsumableQueryDto;
import edu.sjtu.naocemis.dto.UserDto;
import edu.sjtu.naocemis.entity.Consumable;
import edu.sjtu.naocemis.entity.ConsumableProduct;
import edu.sjtu.naocemis.service.ConsumableService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.hibernate.SQLQuery;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author alan_chen
 * @date 2019/7/29
 */
@Service
@Transactional
public class ConsumableServiceImpl implements ConsumableService {

    private static final Logger log = LoggerFactory.getLogger(ConsumableServiceImpl.class);

    @Autowired
    ConsumableDao consumableDao;
    @Autowired
    ConsumableProductDao consumableProductDao;
    @Autowired
    ConsumableService consumableService;
    @Autowired
    EntityManager entityManager;
    @Override
    public List<Consumable> getById(String id) {
        return consumableDao.findAll();
    }

    /**
     * 保存，先删除
     * @param consumable
     */
    @Override
    public void saveConsumable(Consumable consumable) {
        List<ConsumableProduct> products = consumable.getProducts();
        if (!CollectionUtils.isEmpty(products)) {
            for (ConsumableProduct obj : products) {
                consumableProductDao.save(obj);
            }
        }
        consumableDao.save(consumable);
    }




    @Override
    public Consumable getByFlowId(String flowId) {
        List<Consumable> byFlowId = consumableDao.getByFlowId(flowId);
        if (!CollectionUtils.isEmpty(byFlowId)) return byFlowId.get(0);
        return null;
    }

    @Override
    public void update(Consumable consumable) {
        consumableDao.save(consumable);
    }


    /**
     * 删除易耗品信息
     * @param flowId
     * @return
     */
    @Override
    public boolean deleteByFlowId(String flowId) {
        try {
            Consumable consumableOld = consumableService.getByFlowId(flowId + "");
            if (consumableOld != null) {
                consumableDao.deleteById(consumableOld.getId());
                consumableProductDao.deleteFromComsumableId(consumableOld.getId());
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }


    @Override
    public Page<Consumable> getList(ConsumableQueryDto dto) {
        PageRequest pageRequest = PageRequest.of(dto.getPageNum() - 1, dto.getPageSize());


        Specification<Consumable> spec = (Specification<Consumable>) (root, criteriaQuery, cb) -> {
            List<Predicate> list = new ArrayList<>();

            if (StringUtils.isNotBlank(dto.getApplyerAccount())) {
                list.add(cb.like(root.get("applyerAccount"), "%" + dto.getApplyerAccount() + "%"));
            }
            if (StringUtils.isNotBlank(dto.getDept())) {
                list.add(cb.like(root.get("dept"), "%" + dto.getDept() + "%"));
            }
            if (StringUtils.isNotBlank(dto.getApplyDate())) {
                list.add(cb.equal(root.get("applyDate"), DateUtil.getDateFormat(dto.getApplyDate() )));
            }
            if (StringUtils.isNotEmpty(dto.getStartDate()) && StringUtils.isNotEmpty(dto.getEndDate())) {
                list.add(cb.between(root.get("applyDate"), DateUtil.getDateFormat(dto.getStartDate()),
                        DateUtil.getDateFormat(dto.getEndDate())));
            }

            Predicate[] predicates = new Predicate[list.size()];
            predicates = list.toArray(predicates);
            return cb.and(predicates);
        };
        Page<Consumable> page = consumableDao.findAll(spec, pageRequest);
        if (!CollectionUtils.isEmpty(page.getContent())) {
            for (Consumable consumable : page.getContent()) {
                List<ConsumableProduct> productList = consumableProductDao.getByComsumableId(consumable.getId());
                consumable.setProducts(productList);
            }
        }
        return page;
    }

    @Override
    public List getApplyerList(UserDto userDto) {
        StringBuffer sql = new StringBuffer();
        List<Object> params = new ArrayList<>();
        sql.append("select distinct c.applyer, c.applyer_account applyerAccount from consumable c where 1=1");
        if (StringUtils.isNotBlank(userDto.getApplyer())) {
            sql.append(" and c.applyer=?");
            params.add(userDto.getApplyer());
        }
        if (StringUtils.isNotBlank(userDto.getApplyerAccount())) {
            sql.append(" and c.applyer_account=?");
            params.add(userDto.getApplyerAccount());
        }
        Query query = entityManager.createNativeQuery(sql.toString());
        query.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        for (int i=0; i < params.size(); i++) {
            query.setParameter(i+1, params.get(i));
        }

        return query.getResultList();
    }
}

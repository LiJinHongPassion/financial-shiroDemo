package com.cqut.financial.ssm.service.Impl;

import com.cqut.financial.ssm.dao.base.EntityDao;
import com.cqut.financial.ssm.dao.base.SearchDao;
import com.cqut.financial.ssm.entity.FixedBid;
import com.cqut.financial.ssm.entity.base.Entity;
import com.cqut.financial.ssm.service.IFixedBid;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Li
 * @date 2018/10/23-14:10
 */
public class FixedBidImpl implements IFixedBid {

    @Resource
    private EntityDao entityDao;
    @Resource
    private SearchDao searchDao;

    @Override
    public Map<String, Object> add(Map<String, Object> properties) {
        Map<String, Object> result = new HashMap<>();

        FixedBid fixedBid = new FixedBid();
        fixedBid.setProperties(properties);

        entityDao.save(fixedBid);
        result.put("result","1");
        return result;
    }

    @Override
    public Map<String, Object> delete(String fixed_id) {

        Map<String, Object> result = new HashMap<>();
        entityDao.deleteByID(fixed_id, Entity.getTableName(FixedBid.class),Entity.getPrimaryKey(FixedBid.class));
        result.put("result","1");
        return result;
    }

    @Override
    public Map<String, Object> update(Map<String, Object> properties) {
        Map<String, Object> result = new HashMap<>();
        FixedBid fixedBid = new FixedBid();
        fixedBid.setProperties(properties);

        entityDao.updatePropByID(fixedBid,fixedBid.getFixed_id().toString());
        result.put("result","1");
        return result;
    }

    @Override
    public Map<String, Object> getById(String fixed_id) {
        Map<String, Object> result = new HashMap<>();
        result = entityDao.findByID(new String[]{
                "fixed_id" ,
                "fixed_bid_name" ,
                "year_rate" ,
                "server_date",
                "residual_voting"
                },
                fixed_id,
                Entity.getPrimaryKey(FixedBid.class),
                Entity.getTableName(FixedBid.class));

        if(result.size() == 0 || result == null){
            result.put("result","-1");
        }else{
            result.put("result","1");
        }

        return result;
    }
}

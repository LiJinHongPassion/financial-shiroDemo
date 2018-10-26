package com.cqut.financial.ssm.service;

import java.util.Map;

/**
 * @author Li
 * @date 2018/10/23-14:10
 */
public interface IFixedBid {

    Map<String, Object> add(Map<String, Object> properties);

    Map<String, Object> delete(String fixed_id);

    Map<String, Object> update(Map<String, Object> properties);

    Map<String, Object> getById(String fixed_id);

}

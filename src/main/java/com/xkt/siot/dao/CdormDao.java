/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.dao;

import com.xkt.siot.domain.Cdorm;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 用户数据访问接口实现
 *
 * @author gugia
 * @param <T> 主节点从节点映射关系类
 */
@Repository
public class CdormDao<T> extends BaseDaoImpl<T> {

    public Cdorm findCdormByDeviceid(int did) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cdorm.class);
        criteria.add(Restrictions.eq("did", did));
        return (Cdorm) criteria.uniqueResult();
    }

    public List<Cdorm> findCdormsByCoordinatorid(int cid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cdorm.class);
        criteria.add(Restrictions.eq("cid", cid));
        return criteria.list();
    }
}

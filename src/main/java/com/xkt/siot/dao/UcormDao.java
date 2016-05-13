/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.dao;

import com.xkt.siot.domain.Ucorm;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 用户主节点映射数据访问接口实现
 *
 * @author gugia
 * @param <T> 用户主节点映射关系类
 */
@Repository
public class UcormDao<T> extends BaseDaoImpl<T> {

    public List<Ucorm> findUcormsByUserid(int uid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ucorm.class);
        criteria.add(Restrictions.eq("uid", uid));
        return criteria.list();
    }

    public List<Ucorm> findUcormsByCoordinatorid(int cid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ucorm.class);
        criteria.add(Restrictions.eq("cid", cid));
        return criteria.list();
    }

    public boolean checkDuplication(int uid, int cid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ucorm.class);
        criteria.add(Restrictions.eq("uid", uid));
        criteria.add(Restrictions.eq("cid", cid));
        return criteria.uniqueResult() != null;
    }
}

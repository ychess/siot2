/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.dao;

import com.xkt.siot.domain.Cdorm;
import com.xkt.siot.domain.Cert;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 主节点合法性验证数据访问接口实现
 *
 * @author gugia
 * @param <T> 主节点从节点映射关系类
 */
@Repository
public class CertDao<T> extends BaseDaoImpl<T> {

    public Cert findCert(String eui, String mac) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Cert.class);
        criteria.add(Restrictions.eq("eui", eui));
        criteria.add(Restrictions.eq("mac", mac));
        return (Cert) criteria.uniqueResult();
    }
}

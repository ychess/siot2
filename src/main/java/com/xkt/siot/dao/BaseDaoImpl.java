/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 * 通用数据持久层接口实现
 *
 * @author gugia
 * @param <T> com.xkt.siot.domain中的类型
 */
public class BaseDaoImpl<T> implements BaseDao<T> {

    /* DAO组件进行持久化操作底层依赖的SessionFactory组件 */
    @Resource(name = "sessionFactory")
    protected SessionFactory sessionFactory;

    @Override
    public T get(Class<T> entityClazz, Serializable id) {
        return (T) sessionFactory.getCurrentSession().get(entityClazz, id);
    }

    @Override
    public Serializable save(T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void update(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public void delete(T entity) {
        sessionFactory.getCurrentSession().delete(entity);
    }

    @Override
    public void delete(Class<T> entityClazz, Serializable id) {
        sessionFactory.getCurrentSession().createQuery("delete " + entityClazz.getSimpleName() + " en where en.id = ?0").setParameter("0", id).executeUpdate();
    }

    @Override
    public List<T> findAll(Class<T> entityClazz) {
        return find("select en from " + entityClazz.getSimpleName() + " en");
    }

    @Override
    public T findUnique(Class<T> entityClazz, String col, Object param) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClazz);
        criteria.add(Restrictions.eq(col, param));
        return (T) criteria.uniqueResult();
    }

    @Override
    public List<T> findByPage(Class<T> entityClazz, int num, int page, String order, boolean asc) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClazz);
        if (asc) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        criteria.setFirstResult((page - 1) * num);
        criteria.setMaxResults(num);
        return criteria.list();
    }

    @Override
    public List<T> findByKeywordAndPage(Class<T> entityClazz, String col, String keyword, int num, int page, String order, boolean asc) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClazz);
        criteria.add(Restrictions.eq(col, keyword));
        if (asc) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        criteria.setFirstResult((page - 1) * num);
        criteria.setMaxResults(num);
        return criteria.list();
    }

    @Override
    public List<T> findByValueAndPage(Class<T> entityClazz, String col, String restriction, int value, int num, int page, String order, boolean asc) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(entityClazz);
        switch (restriction) {
            case "eq":
                criteria.add(Restrictions.eq(col, value));
                break;
            case "gt":
                criteria.add(Restrictions.gt(col, value));
                break;
            case "ge":
                criteria.add(Restrictions.ge(col, value));
                break;
            case "lt":
                criteria.add(Restrictions.lt(col, value));
                break;
            case "le":
                criteria.add(Restrictions.le(col, value));
                break;
            default:
                return new ArrayList();
        }
        if (asc) {
            criteria.addOrder(Order.asc(order));
        } else {
            criteria.addOrder(Order.desc(order));
        }
        criteria.setFirstResult((page - 1) * num);
        criteria.setMaxResults(num);
        return criteria.list();
    }

    @Override
    public long findCount(Class<T> entityClazz) {
        List<?> list = find("select count(*) from " + entityClazz.getSimpleName());
        if (list != null && list.size() == 1) {
            return (Long) list.get(0);
        }
        return 0;
    }

    protected int update(String hql) {
        return sessionFactory.getCurrentSession().createQuery(hql).executeUpdate();
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String hql) {
        return (List<T>) sessionFactory.getCurrentSession().createQuery(hql).list();
    }

    @SuppressWarnings("unchecked")
    protected List<T> find(String hql, Object... params) {
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        for (int i = 0, len = params.length; i < len; i++) {
            query.setParameter(i + "", params[i]);
        }
        return (List<T>) query.list();
    }
}

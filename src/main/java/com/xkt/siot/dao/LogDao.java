/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.dao;

import com.xkt.siot.domain.Log;
import com.xkt.siot.domain.Ucorm;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * 日志数据访问接口实现
 *
 * @author gugia
 * @param <T> 日志类
 */
@Repository
public class LogDao<T> extends BaseDaoImpl<T> {

    public List<Log> findByCid(int cid, int num, int page) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Log.class);
        criteria.add(Restrictions.eq("cid", cid));
        criteria.setFirstResult((page - 1) * num);
        criteria.setMaxResults(num);
        return criteria.list();
    }

    public List<Log> findByDid(int did, int num, int page) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Log.class);
        criteria.add(Restrictions.eq("did", did));
        criteria.setFirstResult((page - 1) * num);
        criteria.setMaxResults(num);
        return criteria.list();
    }

    public List<Log> findByUserId(int uid, int num, int page) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Ucorm.class);
        criteria.add(Restrictions.eq("uid", uid));
        List<Ucorm> ucorms = criteria.list();
        criteria = sessionFactory.getCurrentSession().createCriteria(Log.class);
        Criterion crtrns = null;
        for (Ucorm ucorm : ucorms) {
            if (null == crtrns) {
                crtrns = Restrictions.eq("cid", ucorm.getCid());
            } else {
                crtrns = Restrictions.or(crtrns, Restrictions.eq("cid", ucorm.getCid()));
            }
        }
        criteria.add(crtrns);
        criteria.setFirstResult((page - 1) * num);
        criteria.setMaxResults(num);
        return criteria.list();
    }

    public List<Log> findLogsByCidAndHours(int cid, int hour) {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp ago = new Timestamp(System.currentTimeMillis() - hour * 1000 * 3600);
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Log.class);
        criteria.add(Restrictions.eq("cid", cid));
        criteria.add(Restrictions.between("rectime", ago, now));
        return criteria.list();
    }

    public List<Log> findLogsByCidAndDate(int cid, Date date) {
        Date tmp = new Date(date.getTime() + 1000 * 3600 * 24);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp day = Timestamp.valueOf(sdf.format(date));
        Timestamp nextDay = Timestamp.valueOf(sdf.format(tmp));
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Log.class);
        criteria.add(Restrictions.eq("cid", cid));
        criteria.add(Restrictions.between("rectime", day, nextDay));
        return criteria.list();
    }
}

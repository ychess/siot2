/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.service;

import com.xkt.siot.dao.CdormDao;
import com.xkt.siot.dao.LogDao;
import com.xkt.siot.dao.UcormDao;
import com.xkt.siot.domain.Log;
import com.xkt.siot.exception.ServiceException;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author L.X <gugia@qq.com>
 */
@Transactional
@Service
public class LogService {

    Logger logger = LoggerFactory.getLogger(LogService.class);

    @Resource
    LogDao logDao;
    @Resource
    UcormDao ucormDao;
    @Resource
    CdormDao cdormDao;

    public int create(Log log) {
        if (null == log) {
            throw new ServiceException("log不能为null");
        }
        return (Integer) logDao.save(log);
    }

    public void delete(Log log) {
        if (null == log) {
            throw new ServiceException("log不能为null");
        }
        logDao.delete(log);
    }

    public void delete(Integer id) {
        logDao.delete(Log.class, id);
    }

    public Log findById(int id) {
        return (Log) logDao.get(Log.class, id);
    }

    public List<Log> findByPage(int num, int page) {
        return logDao.findByPage(Log.class, num, page, "id", true);
    }

    public List<Log> findByCid(int cid, int num, int page) {
        return logDao.findByCid(cid, num, page);
    }

    public List<Log> findByDid(int did, int num, int page) {
        return logDao.findByDid(did, num, page);
    }

    public List<Log> findByUserId(int uid, int num, int page) {
        return logDao.findByUserId(uid, num, page);
    }

    public void update(Log log) {
        if (null == log) {
            throw new ServiceException("log不能为null");
        }
        logDao.update(log);
    }
}

/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.service;

import com.xkt.siot.dao.CertDao;
import com.xkt.siot.dao.CoordinatorDao;
import com.xkt.siot.dao.UcormDao;
import com.xkt.siot.domain.Coordinator;
import com.xkt.siot.domain.Ucorm;
import com.xkt.siot.exception.ServiceException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 主节点服务
 *
 * @author L.X <gugia@qq.com>
 */
@Transactional
@Service
public class CoordinatorService {

    Logger logger = LoggerFactory.getLogger(CoordinatorService.class);

    @Resource
    CoordinatorDao coordinatorDao;
    @Resource
    CertDao certDao;
    @Resource
    UcormDao ucormDao;

    public int create(Coordinator coordinator) {
        if (null == coordinator) {
            throw new ServiceException("coordinator不能为null");
        } else if (null == coordinator.getEui() || coordinator.getEui().isEmpty()) {
            throw new ServiceException("新建主节点的EUI不能为null");
        }
        return (Integer) coordinatorDao.save(coordinator);
    }

    public void delete(Coordinator coordinator) {
        if (null == coordinator) {
            throw new ServiceException("coordinator不能为null");
        }
        coordinatorDao.delete(coordinator);
    }

    public void delete(Integer id) {
        coordinatorDao.delete(Coordinator.class, id);
    }

    public List<Coordinator> findAll() {
        return coordinatorDao.findAll(Coordinator.class);
    }

    public Coordinator findById(int id) {
        return (Coordinator) coordinatorDao.get(Coordinator.class, id);
    }

    public Coordinator findByEui(String eui) {
        return (Coordinator) coordinatorDao.findUnique(Coordinator.class, "eui", eui);
    }

    public List<Coordinator> findByPageOrderByUpdatetime(int num, int page) {
        return coordinatorDao.findByPage(Coordinator.class, num, page, "updatetime", true);
    }

    public List<Coordinator> findByStatus(int value, int num, int page) {
        return coordinatorDao.findByValueAndPage(Coordinator.class, "status", "eq", value, num, page, "id", true);
    }

    public List<Coordinator> findByUserId(int userId) {
        List<Ucorm> ucorms = ucormDao.findUcormsByUserid(userId);
        List<Coordinator> coordinators = new ArrayList();
        ucorms.stream().forEach((Ucorm ucorm) -> {
            Coordinator coordinator = (Coordinator) coordinatorDao.get(Coordinator.class, ucorm.getCid());
            if (coordinator != null) {
                coordinators.add(coordinator);
            }
        });
        return coordinators;
    }

    public void update(Coordinator coordinator) {
        if (null == coordinator) {
            throw new ServiceException("coordinator不能为null");
        }
        coordinatorDao.update(coordinator);
    }

    public boolean validate(String eui, String mac) {
        return certDao.findCert(eui, mac) != null;
    }

    public int associateUser(int uid, Coordinator coordinator) {
        if (ucormDao.checkDuplication(uid, coordinator.getId())) {
            throw new ServiceException("该coordinator已注册到目标用户");
        }
        Ucorm ucorm = new Ucorm(uid, coordinator.getId());
        return (Integer) ucormDao.save(ucorm);
    }
}

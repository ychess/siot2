/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.service;

import com.xkt.siot.dao.DeviceDao;
import com.xkt.siot.domain.Device;
import com.xkt.siot.exception.ServiceException;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 从节点服务
 *
 * @author L.X <gugia@qq.com>
 */
@Transactional
@Service
public class DeviceService {

    Logger logger = LoggerFactory.getLogger(DeviceService.class);

    @Resource
    DeviceDao deviceDao;

    public int create(Device device) {
        if (null == device) {
            throw new ServiceException("device不能为null");
        } else if (null == device.getEui() || device.getEui().isEmpty()) {
            throw new ServiceException("新建从节点的EUI不能为null");
        }
        return (Integer) deviceDao.save(device);
    }

    public void delete(Device device) {
        if (null == device) {
            throw new ServiceException("device不能为null");
        }
        deviceDao.delete(device);
    }

    public void delete(Integer id) {
        deviceDao.delete(Device.class, id);
    }

    public List<Device> findAll() {
        return deviceDao.findAll(Device.class);
    }

    public Device findById(int id) {
        return (Device) deviceDao.get(Device.class, id);
    }

    public Device findByEui(String eui) {
        return (Device) deviceDao.findUnique(Device.class, "eui", eui);
    }
    
    public List<Device> findByPageOrderByUpdatetime(int num, int page) {
        return deviceDao.findByPage(Device.class, num, page, "updatetime", true);
    }

    public List<Device> findByStatus(int value, int num, int page) {
        return deviceDao.findByValueAndPage(Device.class, "status", "eq", value, num, page, "id", true);
    }

    public void update(Device device) {
        if (null == device) {
            throw new ServiceException("device不能为null");
        }
        deviceDao.update(device);
    }
}

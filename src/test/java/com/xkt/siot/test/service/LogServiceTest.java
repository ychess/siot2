/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.test.service;

import com.xkt.siot.domain.Coordinator;
import com.xkt.siot.domain.Log;
import com.xkt.siot.domain.User;
import com.xkt.siot.service.CoordinatorService;
import com.xkt.siot.service.LogService;
import com.xkt.siot.service.UserService;
import com.xkt.siot.test.BaseTest;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日志服务测试
 *
 * @author L.X <gugia@qq.com>
 */
public class LogServiceTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(LogServiceTest.class);

    @Resource
    UserService userService;
    @Resource
    CoordinatorService coordinatorService;
    @Resource
    LogService logService;

    @Test
    public void findByUserId() {
        User user = new User("test", "123");
        int uid = userService.create(user);
        for (int i = 0; i < 5; i++) {
            Coordinator coordinator = new Coordinator("test" + i, "0.1", 1);
            int cid = coordinatorService.create(coordinator);
            coordinatorService.associateUser(uid, coordinator);
            for (int j = 0; j < 3; j++) {
                Log log = new Log(cid, 1);
                logService.create(log);
            }
        }
        List<Log> logs = logService.findByUserId(uid, 20, 1);
        logger.info("通过logService找到 {} 条日志", logs.size());
        logs.stream().forEach((Log log) -> {
            logger.info(log.getId().toString());
        });
    }
}

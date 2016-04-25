/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.test.service;

import com.xkt.siot.domain.User;
import com.xkt.siot.service.UserService;
import com.xkt.siot.test.BaseTest;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户服务测试
 *
 * @author L.X <gugia@qq.com>
 */
public class UserServiceTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Resource
    UserService userService;

    @Test
    public void create() {
        User user = new User("mama", "123");
        int id = userService.create(user);
        logger.info("创建用户的ID为：{}", id);
    }

    @Test
    public void findByValueAndPage() {
        List<User> users = userService.findByValueAndPage("auth", "eq", 1, 20, 1, "id", true);
        if (users.isEmpty()) {
            logger.info("未找到任何用户");
        } else {
            logger.info("共找到 {} 位用户", users.size());
        }
        users.stream().forEach((User user) -> {
            logger.info(user.getUsername());
        });
    }
}
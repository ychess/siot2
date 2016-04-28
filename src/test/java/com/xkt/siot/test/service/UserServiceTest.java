/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.test.service;

import com.xkt.siot.domain.User;
import com.xkt.siot.service.UserService;
import com.xkt.siot.shiro.UserPasswordService;
import com.xkt.siot.test.BaseTest;
import com.xkt.siot.utils.RandomUserGenerator;
import java.util.List;
import javax.annotation.Resource;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.annotation.Rollback;

/**
 * 用户服务测试
 *
 * @author L.X <gugia@qq.com>
 */
public class UserServiceTest extends BaseTest {

    Logger logger = LoggerFactory.getLogger(UserServiceTest.class);

    @Resource
    UserService userService;
    @Resource
    UserPasswordService passwordService;
    @Resource
    RandomUserGenerator randomUserGenerator;

    @Test
    @Rollback(true)
    public void create() {
        User user = randomUserGenerator.getRandomUser();
        user.setUsername("gugia");
        user.setPassword("123");
        int id = userService.create(user);
        logger.info("创建用户的ID为：{}", id);
    }

    @Test
    @Rollback(true)
    public void createMore() {
        for (int i = 0; i < 30; i++) {
            userService.create(randomUserGenerator.getRandomUser());
        }
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
            logger.debug(user.getUsername());
        });
    }

    @Test
    public void passwordService() {
        String e1 = passwordService.encryptPassword("123");
        logger.info("密码加密后为 {}", e1);
        logger.info("密码匹配结果 {}", passwordService.passwordsMatch("123", e1));
        PasswordService ps = new DefaultPasswordService();
        String e2 = ps.encryptPassword("123");
        logger.info("密码加密后为 {}", e2);
        logger.info("密码匹配结果 {}", ps.passwordsMatch("123", e2));
    }
}

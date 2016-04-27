/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.utils;

import com.xkt.siot.domain.User;
import java.text.ParseException;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 随机用户生成器
 *
 * @author gugia
 */
@Service
public class RandomUserGenerator {

    final Logger logger = LoggerFactory.getLogger(RandomUserGenerator.class);

    public User getRandomUser() {
        Date birthday;
        try {
            birthday = RandomPersonHelper.getRandomDate("1950-01-01", "2008-12-12");
        } catch (ParseException ex) {
            logger.error("RandomPersonHelper日期转换失败", ex);
            return null;
        }
        String name = RandomPersonHelper.getRandomName();
        boolean sex = RandomPersonHelper.getRandomSex();
        User user = new User(name, "123", sex, RandomPersonHelper.getRandomChineseName(sex), "用户未填写",
                birthday, RandomPersonHelper.getRandomEmailByUserName(name), RandomPersonHelper.getRandomPhoneNumber(),
                null, null, null, 0, 1, null);
        return user;
    }
}

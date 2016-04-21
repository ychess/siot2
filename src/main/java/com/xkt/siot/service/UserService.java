/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.service;

import com.xkt.siot.dao.UserDao;
import com.xkt.siot.domain.User;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户服务
 *
 * @author L.X <gugia@qq.com>
 */
@Transactional
@Service
public class UserService {

    @Resource
    UserDao userDao;

    public int createUser(User user) {
        return (Integer) userDao.save(user);
    }

    public List<User> findAll() {
        return userDao.findAll(User.class);
    }

    public User findUserById(int id) {
        return (User) userDao.get(User.class, id);
    }

    public User findUserByName(String name) {
        return (User) userDao.findUnique(User.class, "name", name);
    }
}

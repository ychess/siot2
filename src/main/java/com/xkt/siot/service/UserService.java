/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.service;

import com.xkt.siot.dao.UserDao;
import com.xkt.siot.domain.User;
import com.xkt.siot.exception.ServiceException;
import com.xkt.siot.shiro.UserPasswordService;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserDao userDao;
    @Resource
    UserPasswordService passwordService;

    public int create(User user) {
        if (null == user) {
            throw new ServiceException("user不能为null");
        } else if (user.getUsername() == null || user.getPassword() == null) {
            throw new ServiceException("新建用户的账号和密码属性不能为null");
        }
        String password = passwordService.encryptPassword(user.getPassword());
        user.setPassword(password);
        return (Integer) userDao.save(user);
    }

    public void delete(User user) {
        if (null == user) {
            throw new ServiceException("user不能为null");
        }
        userDao.delete(user);
    }

    public void delete(Integer id) {
        userDao.delete(User.class, id);
    }

    public List<User> findAll() {
        return userDao.findAll(User.class);
    }

    public User findById(int id) {
        return (User) userDao.get(User.class, id);
    }

    public User findByName(String name) {
        return (User) userDao.findUnique(User.class, "username", name);
    }

    public List<User> findByPage(int num, int page, String order, boolean asc) {
        return userDao.findByPage(User.class, num, page, order, asc);
    }

    public List<User> findByKeywordAndPage(String col, String keyword, int num, int page, String order, boolean asc) {
        return userDao.findByKeywordAndPage(User.class, col, keyword, num, page, order, asc);
    }

    public List<User> findByValueAndPage(String col, String restriction, int value, int num, int page, String order, boolean asc) {
        return userDao.findByValueAndPage(User.class, col, restriction, value, num, page, order, asc);
    }

    public void update(User user) {
        if (null == user) {
            throw new ServiceException("user不能为null");
        }
        userDao.update(user);
    }
    
    public boolean updatePassword(int id, String oldp, String newp) {
        User user = (User) userDao.get(User.class, id);
        if (null == user) {
            throw new ServiceException("没有 <id=" + id + "> 的用户");
        }
        if (passwordService.passwordsMatch(oldp, user.getPassword())) {
            userDao.updatePassword(id, passwordService.encryptPassword(newp));
            return true;
        }
        return false;
    }
    
    public String getRole(int auth) {
        switch (auth) {
            case 1:
                return "ROLE_USER";
            case 2:
                return "ROLE_HOUSEHOLDER";
            case 3:
                return "ROLE_ADMIN";
            default:
                return "ROLE_UNKNOWN";
        }
    }
}

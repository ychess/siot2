/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.dao;

import org.springframework.stereotype.Repository;

/**
 * 用户数据访问接口实现
 *
 * @author gugia
 * @param <T> 用户类
 */
@Repository
public class UserDao<T> extends BaseDaoImpl<T> {

    public void updatePassword(int id, String password) {
        update("update User e set e.password='" + password + "' where e.id=" + id);
    }
}

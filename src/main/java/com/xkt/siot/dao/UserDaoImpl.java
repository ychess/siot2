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
 * @param <T> com.xkt.siot.domain中的类型
 */
@Repository
public class UserDaoImpl<T> extends BaseDaoImpl<T> implements UserDao<T> {

}

/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.dao;

import java.io.Serializable;
import java.util.List;

/**
 * 通用数据持久层接口
 *
 * @author L.X <gugia@qq.com>
 * @param <T> com.xkt.siot.domain中的类型
 */
public interface BaseDao<T> {

    T get(Class<T> entityClazz, Serializable id);

    Serializable save(T entity);

    void update(T entity);

    void delete(T entity);

    void delete(Class<T> entityClazz, Serializable id);

    List<T> findAll(Class<T> entityClazz);

    T findUnique(Class<T> entityClazz, String col, Object param);

    List<T> findByPage(Class<T> entityClazz, int num, int page, String order, boolean asc);

    List<T> findByKeywordAndPage(Class<T> entityClazz, String col, String keyword, int num, int page, String order, boolean asc);

    List<T> findByValueAndPage(Class<T> entityClazz, String col, String restriction, int value, int num, int page, String order, boolean asc);

    long findCount(Class<T> entityClazz);
}

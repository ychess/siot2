/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.keepalive;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;

/**
 * 心跳连接工厂实现类
 *
 * @author L.X <gugia@qq.com>
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    @Override
    public boolean isRequest(IoSession is, Object o) {
        return o.equals(KeepAliveProtocol.REQUEST);
    }

    @Override
    public boolean isResponse(IoSession is, Object o) {
        return o.equals(KeepAliveProtocol.RESPONSE);
    }

    @Override
    public Object getRequest(IoSession is) {
        return null;
    }

    @Override
    public Object getResponse(IoSession is, Object o) {
        return KeepAliveProtocol.RESPONSE;
    }
}

/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.keepalive;

/**
 * 心跳连接传输协议
 *
 * @author L.X <gugia@qq.com>
 */
public class KeepAliveProtocol {

    public static final int INTERVAL = 8;
    public static final int TIMEOUT = 9;
    public static final String REQUEST = "SIOTKEEPALIVEREQ";
    public static final String RESPONSE = "SIOTKEEPALIVERESP";
}

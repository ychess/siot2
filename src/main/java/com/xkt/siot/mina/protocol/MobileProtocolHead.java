/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.protocol;

/**
 * 移动端通讯约定类型
 *
 * @author L.X <gugia@qq.com>
 */
public class MobileProtocolHead {

    /**
     * 通过账户名和密码获得授权
     */
    public static final int AUTHORIZATION = 1;
    
    /**
     * 移动端下线并安全退出
     */
    public static final int QUIT = 99;
    
    
}
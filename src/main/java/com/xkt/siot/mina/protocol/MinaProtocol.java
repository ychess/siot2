/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.protocol;

/**
 * 通讯协议抽象类
 *
 * @author L.X <gugia@qq.com>
 */
public abstract class MinaProtocol {

    protected int head;
    protected int userId;
    protected Object payload;
    
    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

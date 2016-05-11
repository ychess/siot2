/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.event;

import java.util.EventObject;

/**
 * 移动端事件
 *
 * @author L.X <gugia@qq.com>
 */
public class MobileEvent extends EventObject {

    private int userId;
    private int head;
    private Object payload;

    public MobileEvent(Object source, int userId, int head, Object payload) {
        super(source);
        this.userId = userId;
        this.head = head;
        this.payload = payload;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHead() {
        return head;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

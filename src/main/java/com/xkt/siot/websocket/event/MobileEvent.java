/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.event;

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

    public MobileEvent(Object source, int userId, Object payload) {
        super(source);
        this.userId = userId;
        this.payload = payload;
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

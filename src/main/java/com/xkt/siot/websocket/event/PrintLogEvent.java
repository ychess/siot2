/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.event;

import java.util.EventObject;

/**
 * 通过WebSocket打印事件
 *
 * @author L.X <gugia@qq.com>
 */
public class PrintLogEvent extends EventObject {

    private String msg;

    public PrintLogEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

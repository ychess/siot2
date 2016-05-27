/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.protocol;

/**
 * 主节点服务器通讯协议
 *
 * @author L.X <gugia@qq.com>
 */
public class CoordinatorProtocol {

    private String eui;
    private String mac;
    private int userId;
    private int head;
    private boolean request;
    private Object payload;

    public String getEui() {
        return eui;
    }

    public void setEui(String eui) {
        this.eui = eui;
    }

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
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

    public boolean isRequest() {
        return request;
    }

    public void setRequest(boolean request) {
        this.request = request;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }
    
}

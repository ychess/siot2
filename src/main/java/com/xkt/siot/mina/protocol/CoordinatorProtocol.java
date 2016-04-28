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
    private int head;
    private int type;
    private Object payload;

    /**
     * @return the eui
     */
    public String getEui() {
        return eui;
    }

    /**
     * @param eui the eui to set
     */
    public void setEui(String eui) {
        this.eui = eui;
    }

    /**
     * @return the mac
     */
    public String getMac() {
        return mac;
    }

    /**
     * @param mac the mac to set
     */
    public void setMac(String mac) {
        this.mac = mac;
    }

    /**
     * @return the head
     */
    public int getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(int head) {
        this.head = head;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the payload
     */
    public Object getPayload() {
        return payload;
    }

    /**
     * @param payload the payload to set
     */
    public void setPayload(Object payload) {
        this.payload = payload;
    }
}

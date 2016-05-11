/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.protocol;

/**
 * 移动端服务器通讯协议
 *
 * @author L.X <gugia@qq.com>
 */
public class MobileProtocol {

    private int userId;
    private int head;
    private boolean request;
    private Object payload;

    /**
     * @return the userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the eui to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * @return the request
     */
    public boolean isRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(boolean request) {
        this.request = request;
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

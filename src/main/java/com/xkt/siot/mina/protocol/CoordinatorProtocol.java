/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.protocol;

/**
 * 服务器-主节点通讯协议
 *
 * @author L.X <gugia@qq.com>
 */
public class CoordinatorProtocol extends MinaProtocol {

    private String eui;
    private String mac;

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
}

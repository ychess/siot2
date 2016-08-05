/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 服务器各项设置参数
 *
 * @author L.X <gugia@qq.com>
 */
@Component
public class ServerSettings {

    @Value("#{sp['coordinator.server.port']}")
    private int csPort;
    @Value("#{sp['coordinator.server.timeout']}")
    private int csTimeout;
    @Value("#{sp['coordinator.server.buffersize']}")
    private int csBufferSize;
    
    @Value("#{sp['mobile.server.port']}")
    private int msPort;
    @Value("#{sp['mobile.server.timeout']}")
    private int msTimeout;
    @Value("#{sp['mobile.server.buffersize']}")
    private int msBufferSize;
    
    @Value("#{sp['mobile.udp.port']}")
    private int udpPort;
    
    @Value("#{sp['simple.server.port']}")
    private int ssPort;
    @Value("#{sp['simple.server.timeout']}")
    private int ssTimeout;
    @Value("#{sp['simple.server.buffersize']}")
    private int ssBufferSize;

    public int getCsPort() {
        return csPort;
    }

    public void setCsPort(int csPort) {
        this.csPort = csPort;
    }

    public int getCsTimeout() {
        return csTimeout;
    }

    public void setCsTimeout(int csTimeout) {
        this.csTimeout = csTimeout;
    }

    public int getCsBufferSize() {
        return csBufferSize;
    }

    public void setCsBufferSize(int csBufferSize) {
        this.csBufferSize = csBufferSize;
    }

    public int getMsPort() {
        return msPort;
    }

    public void setMsPort(int msPort) {
        this.msPort = msPort;
    }

    public int getMsTimeout() {
        return msTimeout;
    }

    public void setMsTimeout(int msTimeout) {
        this.msTimeout = msTimeout;
    }

    public int getMsBufferSize() {
        return msBufferSize;
    }

    public void setMsBufferSize(int msBufferSize) {
        this.msBufferSize = msBufferSize;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public int getSsPort() {
        return ssPort;
    }

    public void setSsPort(int ssPort) {
        this.ssPort = ssPort;
    }

    public int getSsTimeout() {
        return ssTimeout;
    }

    public void setSsTimeout(int ssTimeout) {
        this.ssTimeout = ssTimeout;
    }

    public int getSsBufferSize() {
        return ssBufferSize;
    }

    public void setSsBufferSize(int ssBufferSize) {
        this.ssBufferSize = ssBufferSize;
    }
}

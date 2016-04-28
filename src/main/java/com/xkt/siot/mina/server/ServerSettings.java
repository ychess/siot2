/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.server;

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

    /**
     * @return the csPort
     */
    public int getCsPort() {
        return csPort;
    }

    /**
     * @param csPort the csPort to set
     */
    public void setCsPort(int csPort) {
        this.csPort = csPort;
    }

    /**
     * @return the csTimeout
     */
    public int getCsTimeout() {
        return csTimeout;
    }

    /**
     * @param csTimeout the csTimeout to set
     */
    public void setCsTimeout(int csTimeout) {
        this.csTimeout = csTimeout;
    }

    /**
     * @return the csBufferSize
     */
    public int getCsBufferSize() {
        return csBufferSize;
    }

    /**
     * @param csBufferSize the csBufferSize to set
     */
    public void setCsBufferSize(int csBufferSize) {
        this.csBufferSize = csBufferSize;
    }

    /**
     * @return the msPort
     */
    public int getMsPort() {
        return msPort;
    }

    /**
     * @param msPort the msPort to set
     */
    public void setMsPort(int msPort) {
        this.msPort = msPort;
    }

    /**
     * @return the msTimeout
     */
    public int getMsTimeout() {
        return msTimeout;
    }

    /**
     * @param msTimeout the msTimeout to set
     */
    public void setMsTimeout(int msTimeout) {
        this.msTimeout = msTimeout;
    }

    /**
     * @return the msBufferSize
     */
    public int getMsBufferSize() {
        return msBufferSize;
    }

    /**
     * @param msBufferSize the msBufferSize to set
     */
    public void setMsBufferSize(int msBufferSize) {
        this.msBufferSize = msBufferSize;
    }

    /**
     * @return the udpPort
     */
    public int getUdpPort() {
        return udpPort;
    }

    /**
     * @param udpPort the udpPort to set
     */
    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }
}

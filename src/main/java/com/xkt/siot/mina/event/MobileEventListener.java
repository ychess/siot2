/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.event;

import com.xkt.siot.mina.protocol.MobileProtocol;
import java.util.EventListener;
import org.apache.mina.core.session.IoSession;

/**
 * 移动端事件监听器
 *
 * @author L.X <gugia@qq.com>
 */
public class MobileEventListener implements EventListener {

    private IoSession session;
    private int coordinatorId;

    public MobileEventListener(IoSession session, int coordinatorId) {
        super();
        this.session = session;
        this.coordinatorId = coordinatorId;
    }

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public int getCoordinatorId() {
        return coordinatorId;
    }

    public void setCoordinatorId(int coordinatorId) {
        this.coordinatorId = coordinatorId;
    }

    public void eventCallback(MobileEvent event) {
        if (session == null) {
            return;
        }
        MobileProtocol protocol = new MobileProtocol();
        protocol.setUserId(event.getUserId());
        protocol.setHead(event.getHead());
        protocol.setRequest(false);
        protocol.setPayload(event.getPayload());
        session.write(protocol);
    }
}

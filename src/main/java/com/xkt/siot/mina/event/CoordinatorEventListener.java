/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.event;

import com.xkt.siot.mina.protocol.MobileProtocol;
import java.util.EventListener;
import org.apache.mina.core.session.IoSession;

/**
 * 主节点事件监听器
 *
 * @author L.X <gugia@qq.com>
 */
public class CoordinatorEventListener implements EventListener {

    private IoSession session;
    private int userId;

    public CoordinatorEventListener(IoSession session, int userId) {
        super();
        this.session = session;
        this.userId = userId;
    }

    public IoSession getSession() {
        return session;
    }

    public void setSession(IoSession session) {
        this.session = session;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void eventCallback(CoordinatorEvent event) {
        if (session == null) {
            return;
        }
        MobileProtocol protocol = new MobileProtocol();
        session.write(protocol);
    }
}

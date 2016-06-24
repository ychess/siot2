/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.event;

import com.xkt.siot.domain.Coordinator;
import com.xkt.siot.mina.event.MobileEventListener;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

/**
 * Log打印事件管理器
 *
 * @author L.X <gugia@qq.com>
 */
@Component
public class PrintLogEventManager {

    private final Logger logger = LoggerFactory.getLogger(PrintLogEventManager.class);
    private final Set<PrintLogEventListener> listeners;

    public PrintLogEventManager() {
        listeners = new HashSet<>();
    }

    public void addListener(PrintLogEventListener listener) {
        this.listeners.add(listener);
    }

    public void removeListener(WebSocketSession session) {
        Iterator<PrintLogEventListener> it = this.listeners.iterator();
        while (it.hasNext()) {
            PrintLogEventListener listener = it.next();
            if (listener.getSession().equals(session)) {
                this.listeners.remove(listener);
                break;
            }
        }
    }

    protected void notifies(PrintLogEvent event) {
        Iterator<PrintLogEventListener> it = this.listeners.iterator();
        logger.info("当前打印事件监听器数量：{}", listeners.size());
        while (it.hasNext()) {
            PrintLogEventListener listener = it.next();
            listener.eventCallback(event);
        }
    }

    public void invoke(Object source, String msg) {
        PrintLogEvent event = new PrintLogEvent(source, msg);
        notifies(event);
    }
}

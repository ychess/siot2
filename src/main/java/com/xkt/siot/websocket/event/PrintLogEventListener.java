/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.event;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * 打印事件监听
 *
 * @author L.X <gugia@qq.com>
 */
public class PrintLogEventListener implements EventListener {

//    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final Logger logger = LoggerFactory.getLogger(PrintLogEventListener.class);
    private WebSocketSession session;

    public PrintLogEventListener(WebSocketSession session) {
        this.session = session;
    }

    public WebSocketSession getSession() {
        return session;
    }

    public void setSession(WebSocketSession session) {
        this.session = session;
    }

    public void eventCallback(PrintLogEvent event) {
        if (session == null) {
            return;
        }
        try {
//            StringBuilder sb = new StringBuilder();
//            String date = FORMAT.format(new Date());
//            sb.append(date);
//            sb.append(" - ");
//            sb.append(event.getMsg());
            TextMessage message = new TextMessage(event.getMsg());
            session.sendMessage(message);
        } catch (IOException ex) {
            logger.error("SockJS发送消息失败", ex);
        }
    }
}

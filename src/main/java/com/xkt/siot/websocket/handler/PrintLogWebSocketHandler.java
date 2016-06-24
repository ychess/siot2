/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.handler;

import com.xkt.siot.websocket.event.PrintLogEventListener;
import com.xkt.siot.websocket.event.PrintLogEventManager;
import javax.annotation.Resource;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * WebSocket处理器
 *
 * @author L.X <gugia@qq.com>
 */
public class PrintLogWebSocketHandler extends TextWebSocketHandler {

    @Resource
    PrintLogEventManager printLogEventManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        printLogEventManager.addListener(new PrintLogEventListener(session));
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        printLogEventManager.removeListener(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

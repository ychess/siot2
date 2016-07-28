/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xkt.siot.domain.Profile;
import com.xkt.siot.mina.event.CoordinatorEventListener;
import com.xkt.siot.mina.event.CoordinatorEventManager;
import com.xkt.siot.mina.protocol.MinaProtocolHead;
import com.xkt.siot.mina.protocol.MobileProtocol;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

/**
 * 用于移动端通讯的WebSocket处理器
 *
 * @author L.X <gugia@qq.com>
 */
public class MobileWebSocketHandler extends TextWebSocketHandler {

    private final Logger logger = LoggerFactory.getLogger(MobileWebSocketHandler.class);
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    @Resource
    CoordinatorEventManager coordinatorEventManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /* 建立连接后进行的操作 */
        coordinatorEventManager.addListener(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String msg = new String(message.asBytes());
        MobileProtocol protocol = gson.fromJson(msg, MobileProtocol.class);
        switch(protocol.getHead()){
            case MinaProtocolHead.USER_PROFILE_UPDATE: {
                Profile profile = (Profile) protocol.getPayload();
                coordinatorEventManager.invoke(this, 0, 0, profile);
                break;
            }
            case MinaProtocolHead.DEVICE_PROFILE_UPDATE: {
                break;
            }
        }
        if ("订阅".equals(message.toString())) {
            int userId = 0;
            coordinatorEventManager.addListener(new CoordinatorEventListener(session, userId));
        }
        session.sendMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        coordinatorEventManager.removeListener(session);
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}

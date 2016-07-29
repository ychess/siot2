/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.handler;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xkt.siot.domain.Device;
import com.xkt.siot.domain.Profile;
import com.xkt.siot.mina.event.CoordinatorEventManager;
import com.xkt.siot.mina.event.MobileEventManager;
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
    @Resource
    MobileEventManager mobileEventManager;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        /* 建立连接后进行的操作 */
        coordinatorEventManager.addListener(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Integer userId = (Integer) session.getAttributes().get("userId");
        if (userId == null) {
            logger.warn("WebSocketSession {} 中没有userId属性，移动端消息无法处理", session.getRemoteAddress().getHostName());
            return;
        }
        String msg = new String(message.asBytes());
        MobileProtocol protocol = gson.fromJson(msg, MobileProtocol.class);
        switch (protocol.getHead()) {
            case MinaProtocolHead.USER_PROFILE_UPDATE: {
                Profile profile = (Profile) protocol.getPayload();
                mobileEventManager.invoke(this, userId, protocol.getHead(), profile);
                break;
            }
            case MinaProtocolHead.DEVICE_PROFILE_UPDATE: {
                Device device = (Device) protocol.getPayload();
                mobileEventManager.invoke(this, userId, protocol.getHead(), device);
                break;
            }
        }
//        session.sendMessage(message);
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

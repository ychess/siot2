/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.client;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 客户端事件处理
 *
 * @author L.X <gugia@qq.com>
 */
public class FakeClientHandler extends IoHandlerAdapter {

    private final Logger logger = LoggerFactory.getLogger(FakeClientHandler.class);

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        String content = message.toString();
        logger.info("客户端收到消息：{}", content);
        if (content.equalsIgnoreCase("quit")) {
            session.closeNow();
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("客户端发送消息：{}", message);
    }
}

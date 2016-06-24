/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.handler;

import com.xkt.siot.mina.server.config.ServerSettings;
import com.xkt.siot.websocket.event.PrintLogEventManager;
import javax.annotation.Resource;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author L.X <gugia@qq.com>
 */
@Service
public class SimpleTextHandler extends IoHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(SimpleTextHandler.class);

    @Resource
    PrintLogEventManager printLogEventManager;
    @Resource
    ServerSettings serverSettings;

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.info("地址 {} 已连接", session.getRemoteAddress().toString());
        String msg = String.format("地址 %s 已连接", session.getRemoteAddress().toString());
        printLogEventManager.invoke(this, msg);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("连接 {} 已打开", session.getRemoteAddress().toString());
        String msg = String.format("连接 %s 已打开", session.getRemoteAddress().toString());
        printLogEventManager.invoke(this, msg);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("连接 {} 已关闭", session.getRemoteAddress().toString());
        String msg = String.format("连接 %s 已关闭", session.getRemoteAddress().toString());
        printLogEventManager.invoke(this, msg);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info("连接 {} 空闲，空闲时间 {} 秒", session.getRemoteAddress().toString(),
                session.getIdleCount(status) * serverSettings.getSsTimeout() / 1000);
        String msg = String.format("连接 %s 空闲，空闲时间 %s 秒", session.getRemoteAddress().toString(),
                session.getIdleCount(status) * serverSettings.getSsTimeout() / 1000);
        printLogEventManager.invoke(this, msg);
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.error("捕捉到错误", cause);
        String msg = String.format("捕捉到错误: %s", cause.getCause());
        printLogEventManager.invoke(this, msg);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        if (null == message) {
            throw new NullPointerException("接收到的信息为空");
        }
        logger.info("从连接 {} 收到信息：{}", session.getRemoteAddress().toString(), message);
        String msg = String.format("从连接 %s 收到信息：%s", session.getRemoteAddress().toString(), message);
        printLogEventManager.invoke(this, msg);
        session.write(message);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("已向连接 {} 发送信息：{}", session.getRemoteAddress().toString(), message);
        String msg = String.format("已向连接 %s 发送信息：%s", session.getRemoteAddress().toString(), message);
        printLogEventManager.invoke(this, msg);
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        logger.info("连接 {} 的输入已关闭", session.getRemoteAddress().toString());
        String msg = String.format("连接 %s 的输入已关闭", session.getRemoteAddress().toString());
        printLogEventManager.invoke(this, msg);
        session.closeNow();
    }
}

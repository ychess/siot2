/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.server;

import com.xkt.siot.mina.config.ServerSettings;
import com.xkt.siot.mina.handler.SimpleTextHandler;
import com.xkt.siot.websocket.event.PrintLogEventManager;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 简单文本服务器，用于测试
 *
 * @author L.X <gugia@qq.com>
 */
@Component
public class SimpleTextServer implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(SimpleTextServer.class);
    private IoAcceptor ioAcceptor;

    @Resource
    ServerSettings serverSettings;
    @Resource
    SimpleTextHandler simpleTextHandler;
    @Resource
    PrintLogEventManager printLogEventManager;

    @Override
    public void run() {
        logger.info("简单文本服务器启动中...");
        printLogEventManager.invoke(this, "简单文本服务器启动中...");
        ioAcceptor = new NioSocketAcceptor();
        ioAcceptor.getSessionConfig().setReadBufferSize(serverSettings.getSsBufferSize());
        ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, serverSettings.getSsTimeout());
        // 设置日志记录器
        ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter(SimpleTextServer.class));
        // 设置编码过滤器
        TextLineCodecFactory factory = new TextLineCodecFactory(Charset.forName("UTF-8"));
        ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(factory));
        ioAcceptor.setHandler(simpleTextHandler);//指定业务逻辑处理器
        try {
            ioAcceptor.bind(new InetSocketAddress(serverSettings.getSsPort()));//设置端口号并开始接受请求
        } catch (IOException ex) {
            logger.info("简单文本服务器捕捉到错误", ex);
            String msg = String.format("简单文本服务器捕捉到错误：%s", ex.getMessage());
            printLogEventManager.invoke(this, msg);
        }
    }

    public void stop() {
        if (ioAcceptor.isActive()) {
            ioAcceptor.dispose(true);
            logger.info("简单文本服务器已停止");
            printLogEventManager.invoke(this, "简单文本服务器已停止");
        }
    }
}

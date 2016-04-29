/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.server;

import com.xkt.siot.mina.codec.CoordinatorCodecFactory;
import com.xkt.siot.mina.codec.CoordinatorProtocolDecoder;
import com.xkt.siot.mina.codec.CoordinatorProtocolEncoder;
import com.xkt.siot.mina.handler.CoordinatorHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 主节点服务器
 *
 * @author L.X <gugia@qq.com>
 */
@Component
public class CoordinatorServer implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(CoordinatorServer.class);

    @Resource
    ServerSettings serverSettings;
    @Resource
    CoordinatorHandler coordinatorHandler;

    @Override
    public void run() {
        logger.info("主节点服务器启动中...");
        IoAcceptor ioAcceptor = new NioSocketAcceptor();
        ioAcceptor.getSessionConfig().setReadBufferSize(serverSettings.getCsBufferSize());
        ioAcceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        // 设置日志记录器
        ioAcceptor.getFilterChain().addLast("logger", new LoggingFilter(CoordinatorServer.class));
        // 设置编码过滤器
        CoordinatorCodecFactory factory = new CoordinatorCodecFactory(
                new CoordinatorProtocolEncoder(Charset.forName("UTF-8")),
                new CoordinatorProtocolDecoder(Charset.forName("UTF-8")));
        ioAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(factory));
        ioAcceptor.setHandler(coordinatorHandler);//指定业务逻辑处理器
        try {
            ioAcceptor.bind(new InetSocketAddress(serverSettings.getCsPort()));//设置端口号并开始接受请求
        } catch (IOException ex) {
            logger.info("主节点服务器捕捉到错误", ex);
        }
    }

}

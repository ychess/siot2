/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.client;

import com.xkt.siot.mina.server.config.ServerSettings;
import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * 测试客户端
 *
 * @author L.X <gugia@qq.com>
 */
public class FakeClient implements Runnable {

    @Resource
    ServerSettings serverSettings;

    @Override
    public void run() {
        NioSocketConnector connector = new NioSocketConnector();
        connector.getFilterChain().addLast("logger", new LoggingFilter(FakeClient.class));
        connector.getFilterChain().addLast("codec", new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));
        connector.setConnectTimeoutCheckInterval(10);// 设置连接超时检查时间
        connector.setHandler(new FakeClientHandler());
        ConnectFuture cf = connector.connect(new InetSocketAddress(829));// 建立连接
        cf.awaitUninterruptibly();// 等待连接创建完成
        cf.getSession().write("hello!");
        cf.getSession().write("quit");
        //cf.getSession().closeOnFlush();
        cf.getSession().getCloseFuture().awaitUninterruptibly();// 等待连接断开
        connector.dispose();// 释放连接
    }
}

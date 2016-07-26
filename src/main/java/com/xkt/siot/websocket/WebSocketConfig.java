/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket;

import com.xkt.siot.websocket.handler.MobileWebSocketHandler;
import com.xkt.siot.websocket.handler.PrintLogWebSocketHandler;
import com.xkt.siot.websocket.interceptor.MobileWebSocketInterceptor;
import com.xkt.siot.websocket.interceptor.PrintLogWebSocketInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * WebSocket配置类
 *
 * @author L.X <gugia@qq.com>
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        /* SimpleTextServer的日志打印WebSocket服务 */
        registry.addHandler(printLogWebSocketHandler(), "/printlog-ws").addInterceptors(new PrintLogWebSocketInterceptor());
        registry.addHandler(printLogWebSocketHandler(), "/sockjs/printlog-ws").addInterceptors(new PrintLogWebSocketInterceptor())
                .withSockJS();
        /* 移动端和服务器之间建立的双向WebSocket通讯 */
        registry.addHandler(mobileWebSocketHandler(), "/mobile-ws").addInterceptors(new MobileWebSocketInterceptor());
        registry.addHandler(mobileWebSocketHandler(), "/sockjs/mobile-ws").addInterceptors(new MobileWebSocketInterceptor())
                .withSockJS();
    }

    @Bean
    public WebSocketHandler printLogWebSocketHandler() {
        return new PrintLogWebSocketHandler();
    }

    @Bean
    public WebSocketHandler mobileWebSocketHandler() {
        return new MobileWebSocketHandler();
    }
}

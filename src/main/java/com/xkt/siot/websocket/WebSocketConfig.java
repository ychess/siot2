/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket;

import com.xkt.siot.websocket.handler.PrintLogWebSocketHandler;
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
        registry.addHandler(printLogWebSocketHandler(), "/webSocketServer").addInterceptors(new PrintLogWebSocketInterceptor());
        registry.addHandler(printLogWebSocketHandler(), "/sockjs/webSocketServer").addInterceptors(new PrintLogWebSocketInterceptor())
                .withSockJS();
    }

    @Bean
    public WebSocketHandler printLogWebSocketHandler() {
        return new PrintLogWebSocketHandler();
    }
}

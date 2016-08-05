/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.websocket.interceptor;

import com.xkt.siot.domain.User;
import com.xkt.siot.service.UserService;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;

/**
 * 用于移动端通讯的WebSocket拦截器
 *
 * @author L.X <gugia@qq.com>
 */
public class MobileWebSocketInterceptor extends HttpSessionHandshakeInterceptor {

    private final Logger logger = LoggerFactory.getLogger(MobileWebSocketInterceptor.class);

    @Resource
    UserService userService;

    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        if (request instanceof ServletServerHttpRequest) {
            ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
            HttpSession session = servletRequest.getServletRequest().getSession(false);
            if (session != null) {
                //使用userName区分WebSocketHandler，以便定向发送消息
                String userName = (String) session.getAttribute("username");
                User user = userService.findByName(userName);
                if (user == null) {
                    return false;
                } else {
                    attributes.put("userId", user.getId());
                }
            } else {
                logger.warn("HttpSession为空，握手失败!");
                return false;
            }
        }
        //request.getURI().parseServerAuthority();//not finish
        return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        super.afterHandshake(request, response, wsHandler, exception);
    }
}

/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.handler;

import com.xkt.siot.domain.Log;
import com.xkt.siot.mina.protocol.CoordinatorProtocol;
import com.xkt.siot.mina.protocol.CoordinatorProtocolHead;
import com.xkt.siot.service.LogService;
import javax.annotation.Resource;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 主节点服务器事件处理服务
 *
 * @author L.X <gugia@qq.com>
 */
@Service
public class CoordinatorHandler extends IoHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(CoordinatorHandler.class);
    
    @Resource
    LogService logService;

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.info("地址 {} 已连接", session.getRemoteAddress().toString());// 显示客户端的ip和端口
        //订阅移动端事件
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("地址为 {} 的连接已关闭", session.getRemoteAddress().toString());
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        logger.info("IDLE：{}", session.getIdleCount(status));
    }

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.error("捕捉到错误", cause);
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        CoordinatorProtocol protocol = (CoordinatorProtocol) message;
        if (protocol.isRequest()) {
            switch (protocol.getHead()) {
                case CoordinatorProtocolHead.SENSOR_DATA:
                    Log log = (Log) protocol.getPayload();
                    logService.create(log);
                    break;
                case CoordinatorProtocolHead.NETWORK_START_FAILED:
                    break;
                case CoordinatorProtocolHead.CHILD_NONE:
                    break;
                case CoordinatorProtocolHead.CHILD_JOIN:
                    break;
                case CoordinatorProtocolHead.CHILD_LEFT:
                    break;
                case CoordinatorProtocolHead.COORDINATOR_INFO_REPORT:
                    break;
                case CoordinatorProtocolHead.COORDINATOR_INFO_UPDATE:
                    break;
                case CoordinatorProtocolHead.COORDINATOR_FIRMWARE_UPDATE:
                    break;
                case CoordinatorProtocolHead.USER_PROFILE_REPORT:
                    break;
                case CoordinatorProtocolHead.USER_PROFILE_UPDATE:
                    break;
                case CoordinatorProtocolHead.DEVICE_INFO_REPORT:
                    break;
                case CoordinatorProtocolHead.DEVICE_INFO_UPDATE:
                    break;
                case CoordinatorProtocolHead.DEVICE_FIRMWARE_UPDATE:
                    break;
                case CoordinatorProtocolHead.MOTION_ALARM:
                    break;
                case CoordinatorProtocolHead.HUMIDITY_ALARM:
                    break;
                case CoordinatorProtocolHead.TEMPERATURE_ALARM:
                    break;
            }
        }
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        // Empty handler
    }

    @Override
    public void inputClosed(IoSession session) throws Exception {
        session.close(true);
    }
}

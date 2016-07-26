/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.handler;

import com.xkt.siot.domain.Coordinator;
import com.xkt.siot.domain.Log;
import com.xkt.siot.mina.event.CoordinatorEventManager;
import com.xkt.siot.mina.event.MobileEventListener;
import com.xkt.siot.mina.event.MobileEventManager;
import com.xkt.siot.mina.protocol.CoordinatorProtocol;
import com.xkt.siot.mina.protocol.MinaProtocolHead;
import com.xkt.siot.service.CoordinatorService;
import com.xkt.siot.service.LogService;
import java.util.ArrayList;
import java.util.List;
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
    List<Coordinator> validatedCoordinators = new ArrayList();//通过验证的网关集合

    @Resource
    LogService logService;
    @Resource
    CoordinatorService coordinatorService;
    @Resource
    CoordinatorEventManager coordinatorEventManager;
    @Resource
    MobileEventManager mobileEventManager;

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
        Coordinator coordinator = coordinatorService.findByEui(protocol.getEui());
        switch (protocol.getHead()) {
            /* 验证主节点是否合法 */
            case MinaProtocolHead.VALIDATION: {
                protocol = validation(protocol, coordinator);
                if (protocol.getPayload().equals("success")) {
                    mobileEventManager.addListener(new MobileEventListener(session, coordinator.getId()));
                }
                session.write(protocol);
                break;
            }
            case MinaProtocolHead.SENSOR_DATA:
            case MinaProtocolHead.NETWORK_START_FAILED:
            case MinaProtocolHead.CHILD_JOIN:
            case MinaProtocolHead.CHILD_LEFT:
            case MinaProtocolHead.MOTION_ALARM:
            case MinaProtocolHead.HUMIDITY_ALARM:
            case MinaProtocolHead.TEMPERATURE_ALARM:
            case MinaProtocolHead.BATTERY_ALARM: {
                if (coordinator != null) {
                    Log log = (Log) protocol.getPayload();
                    logService.create(log);
                    protocol.setPayload("success");
                    session.write(protocol);
                    coordinatorEventManager.invoke(this, coordinator.getId(), protocol.getHead(), log);
                    //mobileEventManager.invoke(this, 1, protocol.getHead(), protocol.getPayload());
                }
                break;
            }
            case MinaProtocolHead.USER_PROFILE_UPDATE:
            case MinaProtocolHead.DEVICE_PROFILE_UPDATE: {
                if (coordinator != null) {
                    Log log = (Log) protocol.getPayload();
                    logService.create(log);
                    protocol.setPayload("success");
                    session.write(protocol);
                    mobileEventManager.invoke(this, 1, protocol.getHead(), protocol.getPayload());
                }
                break;
            }
            default: {
                /* 协议类型于MinaProtocolHead任意一种均不相等 */
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

    public CoordinatorProtocol validation(CoordinatorProtocol protocol, Coordinator coordinator) {
        if (coordinator != null) { //if存在eui匹配的主节点
            if (!validatedCoordinators.contains(coordinator)) { //如果已验证的主节点集合中不包含该节点
                validatedCoordinators.add(coordinator);//则添加该节点
            }
            protocol.setPayload("success");
        } else {
            boolean result = coordinatorService.validate(protocol.getEui(), protocol.getMac());//验证eui和mac地址
            if (result) { //合法：新建条目，返回成功消息
                int coordinatorId = coordinatorService.create((Coordinator) protocol.getPayload()); //数据库新建主节点条目
                Coordinator c = coordinatorService.findById(coordinatorId);
                if (!validatedCoordinators.contains(c)) { //如果已验证的主节点集合中不包含该节点
                    validatedCoordinators.add(c);//则添加该节点
                }
                protocol.setPayload("success");
            } else { //非法：返回失败消息
                protocol.setPayload("fail");
            }
        }
        return protocol;
    }
}

/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.event;

import com.xkt.siot.domain.Coordinator;
import com.xkt.siot.service.CoordinatorService;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 主节点事件管理器
 *
 * @author L.X <gugia@qq.com>
 */
@Component
public class CoordinatorEventManager {

    private final Logger logger = LoggerFactory.getLogger(CoordinatorEventManager.class);
    private final Set<CoordinatorEventListener> listeners;

    @Resource
    CoordinatorService coordinatorService;

    public CoordinatorEventManager() {
        this.listeners = new HashSet<>();
    }

    public void addListener(CoordinatorEventListener listener) {
        this.listeners.add(listener);
    }

    protected void notifies(MobileEvent event) {
        Iterator<CoordinatorEventListener> it = this.listeners.iterator();
        logger.info("当前移动端事件监听器数量：{}", listeners.size());
        while (it.hasNext()) {
            CoordinatorEventListener listener = it.next();
            List<Coordinator> coordinators = coordinatorService.findByUserId(event.getUserId());
            Iterator<Coordinator> iterator = coordinators.iterator();
            while (iterator.hasNext()) {
                Coordinator coordinator = iterator.next();
//                if (coordinator.getId() == listener.getCoordinatorId()) {
//                    listener.eventCallback(event);
//                    break;//如果产生移动端事件的用户与订阅移动端事件的主节点ID有关联，则调用监听器的回调函数
//                }
            }
        }
    }

    public void invoke(Object source, int coordinatorId, int head, Object payload) {
        MobileEvent event = new MobileEvent(source, coordinatorId, head, payload);
        notifies(event);
    }
}

/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.event;

import java.util.EventObject;

/**
 * 主节点事件
 *
 * @author L.X <gugia@qq.com>
 */
public class CoordinatorEvent extends EventObject {

    public CoordinatorEvent(Object source) {
        super(source);
    }

}

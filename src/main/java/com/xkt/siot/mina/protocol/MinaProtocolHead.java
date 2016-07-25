/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.protocol;

/**
 * 主节点通讯协议类型
 *
 * @author L.X <gugia@qq.com>
 */
public class MinaProtocolHead {

    /* 以下是设备端发起的事件，其中大部分由服务器（Mina Server）转换成Log类写入数据库，
     * 并通知对应移动端，移动端不需要响应来自设备端的任何请求。
     */
    
    /**
     * 硬件验证，不通知移动端
     */
    public static final int VALIDATION = 20;
    /**
     * 传感器数据，不通知移动端（局域网模式下另说）
     */
    public static final int SENSOR_DATA = 21;
    /**
     * 组网失败
     */
    public static final int NETWORK_START_FAILED = 22;
    /**
     * 子节点加入网络
     */
    public static final int CHILD_JOIN = 23;
    /**
     * 子节点离开网络
     */
    public static final int CHILD_LEFT = 24;
    /**
     * 运动报警
     */
    public static final int MOTION_ALARM = 25;
    /**
     * 温度报警
     */
    public static final int TEMPERATURE_ALARM = 26;
    /**
     * 湿度报警
     */
    public static final int HUMIDITY_ALARM = 27;
    /**
     * 电池电量报警
     */
    public static final int BATTERY_ALARM = 28;
    
    /* ↓↓↓↓↓ 以下是移动端发起的事件，与设备端不同的是移动端的请求是要求对应网关反馈的，
     * 这些反馈是今后的控制类应用的基础。
     */
    
    /**
     * 用户情景模式变更
     */
    public static final int USER_PROFILE_UPDATE = 61;
    /**
     * 子节点变更
     */
    public static final int DEVICE_PROFILE_UPDATE = 62;

}

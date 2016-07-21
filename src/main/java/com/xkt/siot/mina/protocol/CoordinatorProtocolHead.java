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
public class CoordinatorProtocolHead {
    /**
     * 硬件验证
     */
    public static final int VALIDATION = 20;
    /**
     * 传感器数据
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
    public static final int COORDINATOR_INFO_REPORT = 28;
    public static final int COORDINATOR_INFO_UPDATE = 29;
    public static final int COORDINATOR_FIRMWARE_UPDATE = 30;
    
    public static final int USER_PROFILE_REPORT = 61;
    public static final int USER_PROFILE_UPDATE = 62;
    
    public static final int DEVICE_INFO_REPORT = 71;
    public static final int DEVICE_INFO_UPDATE = 72;
    public static final int DEVICE_FIRMWARE_UPDATE = 73;
    
}

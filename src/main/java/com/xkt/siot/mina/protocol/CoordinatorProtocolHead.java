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

    public static final int VALIDATION = 1;
    
    public static final int SENSOR_DATA = 11;
    
    public static final int NETWORK_START_FAILED = 101;
    public static final int CHILD_NONE = 102;
    public static final int CHILD_JOIN = 103;
    public static final int CHILD_LEFT = 104;
    
    public static final int MOTION_ALARM = 21;
    public static final int TEMPERATURE_ALARM = 22;
    public static final int HUMIDITY_ALARM = 23;
    
    public static final int COORDINATOR_INFO_REPORT = 51;
    public static final int COORDINATOR_INFO_UPDATE = 52;
    public static final int COORDINATOR_FIRMWARE_UPDATE = 53;
    
    public static final int USER_PROFILE_REPORT = 61;
    public static final int USER_PROFILE_UPDATE = 62;
    
    public static final int DEVICE_INFO_REPORT = 71;
    public static final int DEVICE_INFO_UPDATE = 72;
    public static final int DEVICE_FIRMWARE_UPDATE = 73;
    
}

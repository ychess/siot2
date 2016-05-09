/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.codec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xkt.siot.domain.Coordinator;
import com.xkt.siot.domain.Device;
import com.xkt.siot.domain.Log;
import com.xkt.siot.domain.Profile;
import com.xkt.siot.mina.handler.CoordinatorHandler;
import com.xkt.siot.mina.protocol.CoordinatorProtocol;
import com.xkt.siot.mina.protocol.CoordinatorProtocolHead;
import java.nio.charset.Charset;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 主节点服务器协议解码器
 *
 * @author L.X <gugia@qq.com>
 */
public class CoordinatorProtocolDecoder implements MessageDecoder {

    private final Charset charset;
    private final Logger logger = LoggerFactory.getLogger(CoordinatorHandler.class);
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public CoordinatorProtocolDecoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public MessageDecoderResult decodable(IoSession session, IoBuffer in) {
        if (in.remaining() < 8) {
            return MessageDecoderResult.NEED_DATA;
        }
        return MessageDecoderResult.OK;
    }

    @Override
    public MessageDecoderResult decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
        CoordinatorProtocol protocol = gson.fromJson(in.getString(charset.newDecoder()), CoordinatorProtocol.class);
        switch (protocol.getHead()) {
            case CoordinatorProtocolHead.SENSOR_DATA:
            case CoordinatorProtocolHead.NETWORK_START_FAILED:
            case CoordinatorProtocolHead.CHILD_NONE:
            case CoordinatorProtocolHead.CHILD_JOIN:
            case CoordinatorProtocolHead.CHILD_LEFT:
            case CoordinatorProtocolHead.MOTION_ALARM:
            case CoordinatorProtocolHead.HUMIDITY_ALARM:
            case CoordinatorProtocolHead.TEMPERATURE_ALARM: {
                Log log = gson.fromJson(protocol.getPayload().toString(), Log.class);
                protocol.setPayload(log);
                break;
            }
            case CoordinatorProtocolHead.COORDINATOR_INFO_UPDATE:
                break;
            case CoordinatorProtocolHead.COORDINATOR_FIRMWARE_UPDATE:
                break;
            case CoordinatorProtocolHead.USER_PROFILE_UPDATE:
                break;
            case CoordinatorProtocolHead.DEVICE_INFO_UPDATE:
                break;
            case CoordinatorProtocolHead.DEVICE_FIRMWARE_UPDATE:
                break;
            case CoordinatorProtocolHead.COORDINATOR_INFO_REPORT:
                if (!protocol.isRequest()) {
                    Coordinator coordinator = gson.fromJson(protocol.getPayload().toString(), Coordinator.class);
                    protocol.setPayload(coordinator);
                }
                break;
            case CoordinatorProtocolHead.USER_PROFILE_REPORT:
                if (!protocol.isRequest()) {
                    Profile profile = gson.fromJson(protocol.getPayload().toString(), Profile.class);
                    protocol.setPayload(profile);
                }
                break;
            case CoordinatorProtocolHead.DEVICE_INFO_REPORT:
                if (!protocol.isRequest()) {
                    Device device = gson.fromJson(protocol.getPayload().toString(), Device.class);
                    protocol.setPayload(device);
                }
                break;
        }

        out.write(protocol);
        return MessageDecoderResult.OK;
    }

    @Override
    public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
    }
}

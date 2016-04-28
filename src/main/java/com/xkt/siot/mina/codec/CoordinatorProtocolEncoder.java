/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.codec;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.xkt.siot.mina.protocol.CoordinatorProtocol;
import java.nio.charset.Charset;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.filter.codec.demux.MessageEncoder;

/**
 * 主节点服务器协议编码器
 *
 * @author L.X <gugia@qq.com>
 */
public class CoordinatorProtocolEncoder implements MessageEncoder {

    private final Charset charset;
    private final Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    public CoordinatorProtocolEncoder(Charset charset) {
        this.charset = charset;
    }

    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        IoBuffer buffer = IoBuffer.allocate(1024).setAutoExpand(true);
        if (message instanceof CoordinatorProtocol) {
            CoordinatorProtocol protocol = (CoordinatorProtocol) message;
            buffer.putString(gson.toJson(protocol), charset.newEncoder());
        }
        buffer.flip();
        out.write(buffer);
    }
}

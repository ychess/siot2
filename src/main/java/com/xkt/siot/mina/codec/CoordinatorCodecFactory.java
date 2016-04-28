/*
 * Copyright 2016 XKT Ltd., Co.
 * Licensed under the Apache License 2.0.
 */
package com.xkt.siot.mina.codec;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.demux.DemuxingProtocolDecoder;
import org.apache.mina.filter.codec.demux.DemuxingProtocolEncoder;
import org.apache.mina.filter.codec.demux.MessageDecoder;
import org.apache.mina.filter.codec.demux.MessageDecoderFactory;
import org.apache.mina.filter.codec.demux.MessageEncoder;
import org.apache.mina.filter.codec.demux.MessageEncoderFactory;

/**
 * 主节点服务器编码解码工厂
 *
 * @author L.X <gugia@qq.com>
 */
public class CoordinatorCodecFactory implements ProtocolCodecFactory {

    private final DemuxingProtocolEncoder encoder = new DemuxingProtocolEncoder();
    private final DemuxingProtocolDecoder decoder = new DemuxingProtocolDecoder();

    public CoordinatorCodecFactory(CoordinatorProtocolEncoder encoder, CoordinatorProtocolDecoder decoder) {
        this.encoder.addMessageEncoder(CoordinatorProtocolEncoder.class, encoder);
        this.decoder.addMessageDecoder(decoder);
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }

    public void addMessageEncoder(Class<?> messageType, Class<? extends MessageEncoder> encoderClass) {
        this.encoder.addMessageEncoder(messageType, encoderClass);
    }

    public <T> void addMessageEncoder(Class<T> messageType, MessageEncoder<? super T> encoder) {
        this.encoder.addMessageEncoder(messageType, encoder);
    }

    public <T> void addMessageEncoder(Class<T> messageType, MessageEncoderFactory<? super T> factory) {
        this.encoder.addMessageEncoder(messageType, factory);
    }

    public void addMessageEncoder(Iterable<Class<?>> messageTypes, Class<? extends MessageEncoder> encoderClass) {
        for (Class<?> messageType : messageTypes) {
            addMessageEncoder(messageType, encoderClass);
        }
    }

    public <T> void addMessageEncoder(Iterable<Class<? extends T>> messageTypes, MessageEncoder<? super T> encoder) {
        for (Class<? extends T> messageType : messageTypes) {
            addMessageEncoder(messageType, encoder);
        }
    }

    public <T> void addMessageEncoder(Iterable<Class<? extends T>> messageTypes,
            MessageEncoderFactory<? super T> factory) {
        for (Class<? extends T> messageType : messageTypes) {
            addMessageEncoder(messageType, factory);
        }
    }

    public void addMessageDecoder(Class<? extends MessageDecoder> decoderClass) {
        this.decoder.addMessageDecoder(decoderClass);
    }

    public void addMessageDecoder(MessageDecoder decoder) {
        this.decoder.addMessageDecoder(decoder);
    }

    public void addMessageDecoder(MessageDecoderFactory factory) {
        this.decoder.addMessageDecoder(factory);
    }
}

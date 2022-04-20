package com.c.crpc.client;

import com.c.crpc.common.utils.ReflectionUtil;
import com.c.crpc.serialization.Decoder;
import com.c.crpc.serialization.Encoder;

import java.lang.reflect.Proxy;

public class Client {
    private ClientConfig config;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector transportSelector;

    public Client() {
        this(new ClientConfig());
    }

    public Client(ClientConfig config) {
        this.config = config;

        encoder = ReflectionUtil.newInstance(config.getEncoderClass());
        decoder = ReflectionUtil.newInstance(config.getDecoderClass());
        transportSelector = ReflectionUtil.newInstance(config.getTransportSelectorClass());

        transportSelector.init(
                config.getServers(),
                config.getConnectCount(),
                config.getTransportClass()
        );

    }

    public <T> T getProxy(Class<T> clazz) {
        return (T) Proxy.newProxyInstance(
                clazz.getClassLoader(),
                new Class[]{clazz},
                new RemoteInvoker(clazz, encoder, decoder, transportSelector)
        );
    }
}

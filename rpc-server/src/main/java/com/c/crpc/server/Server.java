package com.c.crpc.server;

import com.c.crpc.common.enumeration.ResponseEnum;
import com.c.crpc.common.utils.ReflectionUtil;
import com.c.crpc.protocol.Request;
import com.c.crpc.protocol.Response;
import com.c.crpc.serialization.Decoder;
import com.c.crpc.serialization.Encoder;
import com.c.crpc.transport.RequestHandler;
import com.c.crpc.transport.TransportServer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@Slf4j
public class Server {
    private ServerConfig config;
    private TransportServer transportServer;
    private Encoder encoder;
    private Decoder decoder;
    private ServiceManager serviceManager;
    private ServiceInvoker serviceInvoker;

    private RequestHandler handler;

    public Server() {
        this(new ServerConfig());
    }

    public Server(ServerConfig config) {
        this.config = config;

        handler = new RequestHandler() {
            @Override
            public void onRequest(InputStream receive, OutputStream toResp) {
                Response resp = new Response();
                try {
                    // 1. 从接收里读数据，反序列化成请求
                    byte[] inBytes = IOUtils.readFully(receive, receive.available());
                    Request request = decoder.decode(inBytes, Request.class);
                    log.info("get request : {}", request);
                    // 2. 根据request找服务并调用
                    ServiceInstance serviceInstance = serviceManager.lookup(request);
                    Object returnObject = serviceInvoker.invoke(serviceInstance, request);
                    // 3. 返回调用的结果

                    resp.setData(returnObject);
                    resp.setCode(ResponseEnum.SUCCESS.getCode());
                    resp.setMessage(ResponseEnum.SUCCESS.getMessage());

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                    resp.setCode(ResponseEnum.FAIL.getCode());
                    resp.setMessage("server error : " + e.getClass().getName()
                            + " : " + e.getMessage());
                    throw new RuntimeException(e);
                } finally {
                    byte[] outBytes = encoder.encode(resp);
                    try {
                        toResp.write(outBytes);

                        log.info("server responses to client.");
                    } catch (IOException e) {
                        log.error(e.getMessage(), e);
                    }
                }

            }
        };

        transportServer = ReflectionUtil.newInstance(config.getTransportClass());
        transportServer.init(config.getPort(), handler);

        encoder = ReflectionUtil.newInstance(config.getEncoderClass());
        decoder = ReflectionUtil.newInstance(config.getDecoderClass());

        serviceManager = new ServiceManager();
        serviceInvoker = new ServiceInvoker();
    }

    public <T> void register(Class<T> clazz, T bean) {
        serviceManager.register(clazz, bean);
    }

    public void start() {
        transportServer.start();
    }

    public void stop() {
        transportServer.stop();
    }


}

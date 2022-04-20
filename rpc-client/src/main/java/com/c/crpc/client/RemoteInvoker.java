package com.c.crpc.client;

import com.c.crpc.common.enumeration.ResponseEnum;
import com.c.crpc.protocol.Request;
import com.c.crpc.protocol.Response;
import com.c.crpc.protocol.ServiceDescriptor;
import com.c.crpc.serialization.Decoder;
import com.c.crpc.serialization.Encoder;
import com.c.crpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class RemoteInvoker implements InvocationHandler {
    private Class clazz;
    private Encoder encoder;
    private Decoder decoder;
    private TransportSelector selector;

    public RemoteInvoker(
            Class clazz,
            Encoder encoder,
            Decoder decoder,
            TransportSelector selector
    ) {
        this.clazz = clazz;
        this.encoder = encoder;
        this.decoder = decoder;
        this.selector = selector;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(clazz, method));
        request.setParameters(args);

        Response resp = invokeRequest(request);
        if (resp == null || resp.getCode() != ResponseEnum.SUCCESS.getCode()) {
            throw new IllegalStateException("fail to invoke remote method : " + resp)
        }
        return resp.getData();
    }

    private Response invokeRequest(Request request) {
        TransportClient client = null;
        Response resp = null;
        try {
            client = selector.select();
            byte[] requestBytes = encoder.encode(request);
            InputStream receive = client.write(new ByteArrayInputStream(requestBytes));
            byte[] receiveBytes = IOUtils.readFully(receive, receive.available());
            resp = decoder.decode(receiveBytes, Response.class);

        } catch (IOException e) {
            log.error(e.getMessage(), e);
            resp = new Response();
            resp.setCode(ResponseEnum.FAIL.getCode());
            resp.setMessage("client got error : "
            + e.getClass()
            + " : " + e.getMessage());
        } finally {
            if (client != null) selector.release(client);
        }
        return resp;
    }
}

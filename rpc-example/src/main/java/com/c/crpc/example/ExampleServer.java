package com.c.crpc.example;

import com.c.crpc.example.serviceImpls.CalculateServiceImpl;
import com.c.crpc.example.services.CalculateService;
import com.c.crpc.server.Server;
import com.c.crpc.server.ServerConfig;

public class ExampleServer {

    public static void main(String[] args) {
        ServerConfig config = new ServerConfig();
//        config.setEncoderClass(PlainJavaEncoder.class);
//        config.setDecoderClass(PlainJavaDecoder.class);
//        config.setTransportClass(SocketTransportServer.class);
        Server server = new Server(config);

        server.register(CalculateService.class, new CalculateServiceImpl());
        server.start();

    }
}

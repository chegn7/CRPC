package com.c.crpc.example;

import com.c.crpc.example.serviceImpls.CalculateServiceImpl;
import com.c.crpc.example.services.CalculateService;
import com.c.crpc.server.Server;
import com.c.crpc.server.ServerConfig;

public class ExampleServer {

    public static void main(String[] args) {
        ServerConfig config = new ServerConfig();
<<<<<<< HEAD
//        config.setEncoderClass(PlainJavaEncoder.class);
//        config.setDecoderClass(PlainJavaDecoder.class);
//        config.setTransportClass(SocketTransportServer.class);
=======
>>>>>>> parent of 5b1c02a (增加了原生Java序列化方式)
        Server server = new Server(config);

        server.register(CalculateService.class, new CalculateServiceImpl());
        server.start();

    }
}

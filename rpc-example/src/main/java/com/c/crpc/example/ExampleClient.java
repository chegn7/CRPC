package com.c.crpc.example;

import com.c.crpc.client.Client;
import com.c.crpc.example.services.CalculateService;

public class ExampleClient {
    public static void main(String[] args) {
<<<<<<< HEAD
        ClientConfig config = new ClientConfig();
//        config.setDecoderClass(PlainJavaDecoder.class);
//        config.setEncoderClass(PlainJavaEncoder.class);
//        config.setTransportClass(SocketTransportClient.class);
        Client client = new Client(config);
=======
        Client client = new Client();
>>>>>>> parent of 5b1c02a (增加了原生Java序列化方式)
        CalculateService service = client.getProxy(CalculateService.class);

        System.out.println(service);
//        System.out.println(service.add(1, 2));
//        System.out.println(service.minus(1, 2));
    }
}
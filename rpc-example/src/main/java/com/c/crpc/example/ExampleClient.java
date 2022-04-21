package com.c.crpc.example;

import com.c.crpc.client.Client;
import com.c.crpc.client.ClientConfig;
import com.c.crpc.example.services.CalculateService;

public class ExampleClient {
    public static void main(String[] args) {
        ClientConfig config = new ClientConfig();
//        config.setDecoderClass(PlainJavaDecoder.class);
//        config.setEncoderClass(PlainJavaEncoder.class);
//        config.setTransportClass(SocketTransportClient.class);
        Client client = new Client(config);
        CalculateService service = client.getProxy(CalculateService.class);

        System.out.println(service);
//        System.out.println(service.add(1, 2));
//        System.out.println(service.minus(1, 2));
    }
}
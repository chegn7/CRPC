package com.c.crpc.example;

import com.c.crpc.example.serviceImpls.CalculateServiceImpl;
import com.c.crpc.example.services.CalculateService;
import com.c.crpc.server.Server;

public class ExampleServer {
    public static void main(String[] args) {
        Server server = new Server();

        server.register(CalculateService.class, new CalculateServiceImpl());
        server.start();

    }
}

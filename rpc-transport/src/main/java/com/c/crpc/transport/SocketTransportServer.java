package com.c.crpc.transport;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketTransportServer implements TransportServer {
    RequestHandler handler;
    ServerSocket serverSocket;
    Socket socket;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void start() {
        try {
            while ((socket = serverSocket.accept()) != null) {
                InputStream is = socket.getInputStream();
                OutputStream os = socket.getOutputStream();
                handler.onRequest(is, os);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (serverSocket != null) {
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

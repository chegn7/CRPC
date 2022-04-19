package com.c.crpc.transport;

import com.c.crpc.protocol.Peer;

import java.io.InputStream;

public class HTTPTransportClient implements TransportClient{
    @Override
    public void connect(Peer peer) {
        
    }

    @Override
    public InputStream write(InputStream data) {
        return null;
    }

    @Override
    public void close() {

    }
}

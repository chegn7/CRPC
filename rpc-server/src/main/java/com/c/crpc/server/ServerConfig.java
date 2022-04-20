package com.c.crpc.server;

import com.c.crpc.serialization.Decoder;
import com.c.crpc.serialization.Encoder;
import com.c.crpc.serialization.JSONDecoder;
import com.c.crpc.serialization.JSONEncoder;
import com.c.crpc.transport.HTTPTransportServer;
import com.c.crpc.transport.TransportServer;
import lombok.Data;

@Data
public class ServerConfig {
    private Class<? extends TransportServer> transportClass = HTTPTransportServer.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private int port = 3000;


}

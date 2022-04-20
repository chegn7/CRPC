package com.c.crpc.client;

import com.c.crpc.protocol.Peer;
import com.c.crpc.serialization.Decoder;
import com.c.crpc.serialization.Encoder;
import com.c.crpc.serialization.JSONDecoder;
import com.c.crpc.serialization.JSONEncoder;
import com.c.crpc.transport.HTTPTransportClient;
import com.c.crpc.transport.TransportClient;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
@Data
public class ClientConfig {
    private Class<? extends TransportClient> transportClass = HTTPTransportClient.class;
    private Class<? extends Encoder> encoderClass = JSONEncoder.class;
    private Class<? extends Decoder> decoderClass = JSONDecoder.class;
    private Class<? extends TransportSelector> transportSelectorClass = RandomTransportSelector.class;
    private int connectCount = 1;
    private List<Peer> servers = Arrays.asList(
            new Peer("127.0.0.1", 3000)
    );
}

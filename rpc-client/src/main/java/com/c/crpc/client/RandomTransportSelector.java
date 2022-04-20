package com.c.crpc.client;

import com.c.crpc.common.utils.ReflectionUtil;
import com.c.crpc.protocol.Peer;
import com.c.crpc.transport.TransportClient;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
@Slf4j
public class RandomTransportSelector implements TransportSelector {
    private List<TransportClient> transportClients;

    public RandomTransportSelector() {
        transportClients = new ArrayList<>();
    }


    @Override
    public synchronized void init(List<Peer> peers, int count, Class<? extends TransportClient> clazz) {
        count = Math.max(count, 1);

        for (Peer peer : peers) {
            for (int i = 0; i < count; i++) {
                TransportClient client = ReflectionUtil.newInstance(clazz);
                client.connect(peer);
                transportClients.add(client);
            }
            log.info("connected to server : {}", peer);
        }
    }

    @Override
    public synchronized TransportClient select() {
        int clientId = (int) (Math.random() * transportClients.size());
        return transportClients.remove(clientId);
    }

    @Override
    public synchronized void release(TransportClient client) {
        transportClients.add(client);
    }

    @Override
    public synchronized void close() {
        for (TransportClient client : transportClients) {
            client.close();
        }
        transportClients.clear();
    }
}

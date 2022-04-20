package com.c.crpc.client;

import com.c.crpc.protocol.Peer;
import com.c.crpc.transport.TransportClient;

import java.util.List;

/**
 * server连接选择器，选择连接哪个server
 */
public interface TransportSelector {
    /**
     * 初始化选择器
     * @param peers 服务器节点信息
     * @param count 建立连接数量
     * @param clazz client实现类信息
     */
    void init(List<Peer> peers,
              int count,
              Class<? extends TransportClient> clazz);

    /**
     * 选择一个transport与服务端连接
     *
     * @return TransportClient
     */
    TransportClient select();

    void release(TransportClient client);

    void close();
}

package com.c.crpc.transport;

import com.c.crpc.protocol.Peer;

import java.io.InputStream;

/**
 * 客户端的网络协议需要
 * 1. 创建连接
 * 2. 发送数据，等待返回响应
 * 3. 关闭连接
 */
public interface TransportClient {
    void connect(Peer peer);

    InputStream write(InputStream data);

    void close();
}

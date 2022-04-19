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

    /**
     * 为什么都是输入流？
     * 1. data 是客户端序列化之后经客户端的输出流发送给传输客户端的，对传输客户端是输入
     * 2. 从http协议接收的响应流，对传输客户端，也是写入
     * @param data
     * @return
     */
    InputStream write(InputStream data);

    void close();
}

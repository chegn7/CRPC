package com.c.crpc.transport;

/**
 * 1. 启动监听端口
 * 2. 接收请求，发送响应数据，其中handler需要server自己重写，transport server只负责收发数据
 *
 *    byte[] -> handler process -> decoder -> request
 * 3. 关闭监听
 */
public interface TransportServer {

    void init(int port, RequestHandler handler);

    void start();

    void stop();
}

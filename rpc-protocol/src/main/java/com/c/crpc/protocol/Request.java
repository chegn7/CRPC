package com.c.crpc.protocol;

/**
 * RPC 客户端发送的请求
 */
public class Request {
    /**
     * 描述客户端请求的服务
     */
    private ServiceDescriptor serviceDescriptor;
    /**
     * 请求附带的参数列表
     */
    private Object[] parameters;
}

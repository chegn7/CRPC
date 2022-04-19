package com.c.crpc.serialization;

/**
 * 把对象转为二进制数组
 * 序列化
 */
public interface Encoder {
    byte[] encode(Object obj);
}

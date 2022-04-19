package com.c.crpc.serialization;

public interface Decoder {
    <T> T decode(byte[] bytes, Class<T> clazz);
}

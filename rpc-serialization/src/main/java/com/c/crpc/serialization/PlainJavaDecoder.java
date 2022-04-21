package com.c.crpc.serialization;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

@Slf4j
public class PlainJavaDecoder implements Decoder {
    @Override
    public <T> T decode(byte[] bytes, Class<T> clazz) {
        try (
                ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
                ObjectInputStream ois = new ObjectInputStream(bis);

        ) {
            return (T) ois.readObject();
        } catch (IOException e) {
            log.error("decode io exception : " + e.getMessage(), e);
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            log.error("decode class not found exception : " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}

package com.c.crpc.serialization;

import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

@Slf4j
public class PlainJavaEncoder implements Encoder {
    @Override
    public byte[] encode(Object obj) {
        byte[] bytes = null;
        try (
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(bos);
        ) {
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            log.error("encoder error : " + e.getMessage(), e);
        }
        return bytes;
    }
}

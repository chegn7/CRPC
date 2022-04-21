package com.c.crpc.serialization;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CodecTest {
    TestBean bean;
    Encoder encoder;
    Decoder decoder;
    @Before
    public void init() {
        bean = new TestBean();
        bean.setName("zhangsan");
        bean.setAge(18);
        bean.setAddress("address1 test");
    }


    @Test
    public void JSONEncode() {
        encoder = new JSONEncoder();
        byte[] bytes = encoder.encode(bean);
        assertNotNull(bytes);
    }

    @Test
    public void JSONDecode() {
        encoder = new JSONEncoder();
        byte[] bytes = encoder.encode(bean);
        decoder = new JSONDecoder();
        TestBean decodeBean = decoder.decode(bytes, TestBean.class);
        assertEquals(bean,decodeBean);
    }
}
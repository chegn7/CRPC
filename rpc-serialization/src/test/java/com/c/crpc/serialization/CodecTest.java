package com.c.crpc.serialization;

import com.c.crpc.protocol.Request;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;

public class CodecTest {
//    Response bean;
    Request bean;
//    TestBean bean;
    Encoder encoder;
    Decoder decoder;
    @Before
    public void init() {
        bean = new Request();
//        bean = new Response();
//        bean = new TestBean();
//        bean.setName("zhangsan");
//        bean.setAge(18);
//        bean.setAddress("address1 test");
//        bean.setArgs(new Object[2]);
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
//        assertEquals(bean,decodeBean);
    }

    @Test
    public void JavaEncoder() {
        encoder = new PlainJavaEncoder();
        byte[] encode = encoder.encode(bean);
        System.out.println(Arrays.toString(encode));
    }

    @Test
    public void JavaDecoder() {
        encoder = new PlainJavaEncoder();
        byte[] bytes = encoder.encode(bean);
        decoder = new PlainJavaDecoder();
        Object obj = decoder.decode(bytes, bean.getClass());
        System.out.println(obj);
    }
}
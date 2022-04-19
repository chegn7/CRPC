package com.c.crpc.common.utils;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ReflectionUtilTest {

    @Test
    public void newInstance() {
        TestClass testClass = ReflectionUtil.newInstance(TestClass.class);
        assertNotNull(testClass);
    }

    @Test
    public void getPublicMethods() {
        Method[] methods = ReflectionUtil.getPublicMethods(TestClass.class);
        assertEquals(2, methods.length);
        System.out.println(methods[0].getName());
    }

    @Test
    public void invoke() {
        Method[] methods = ReflectionUtil.getPublicMethods(TestClass.class);
        Method b = methods[0], d = methods[1];
        TestClass testClass = new TestClass();
        System.out.println(ReflectionUtil.invoke(testClass, b));
        System.out.println(ReflectionUtil.invoke(null, d));
    }
}
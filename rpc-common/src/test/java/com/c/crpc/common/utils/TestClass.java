package com.c.crpc.common.utils;

public class TestClass {
    private String a() {
        return "a is private";
    }
    public String b() {
        return "b is public";
    }
    protected String c() {
        return "c is protected";
    }
    public static String d() {
        return "d is static and public";
    }
}

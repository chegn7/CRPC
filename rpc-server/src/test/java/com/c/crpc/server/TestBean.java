package com.c.crpc.server;

public class TestBean implements TestInterface{
    @Override
    public void hello() {
        System.out.println("hello testBean");
    }
}

package com.c.crpc.server;

public interface TestInterface {
    default void hello() {
        System.out.println("hello default");
    }
}

package com.c.crpc.example.services;

public interface CalculateService {
    default int add(int a, int b){
        return  a + b;
    }
    default int minus(int a, int b) {
        return a - b;
    }

}

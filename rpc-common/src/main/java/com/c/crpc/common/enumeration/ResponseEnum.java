package com.c.crpc.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ResponseEnum {
    SUCCESS(0, "ok"),
    OTHER(2);
    int code;
    String message;

    ResponseEnum(int code) {
        this.code = code;
    }
}

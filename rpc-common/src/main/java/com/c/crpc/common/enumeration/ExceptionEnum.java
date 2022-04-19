package com.c.crpc.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionEnum {
    INSTANCE_CREATION_FAILED("Instance creation failed.\t"),
    METHOD_MATCH_FAILED("Method match failed.\t"),
    METHOD_INVOKE_FAILED("Method invoke failed.\t"),
    TRANSPORT_ERROR("Transport error.\t"),
    OTHER("Other reasons.");
    private String exceptionMessage;
}

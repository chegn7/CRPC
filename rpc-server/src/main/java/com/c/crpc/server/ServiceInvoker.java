package com.c.crpc.server;

import com.c.crpc.common.utils.ReflectionUtil;
import com.c.crpc.protocol.Request;

import java.lang.reflect.Method;

public class ServiceInvoker {
    public Object invoke(ServiceInstance serviceInstance, Request request) {
        Method method = serviceInstance.getMethod();
        Object target = serviceInstance.getTarget();
        Object[] parameters = request.getParameters();
        return ReflectionUtil.invoke(target, method, parameters);
    }
}

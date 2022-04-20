package com.c.crpc.server;

import com.c.crpc.common.utils.ReflectionUtil;
import com.c.crpc.protocol.Request;
import com.c.crpc.protocol.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 管理服务器暴露的服务
 */
@Slf4j
public class ServiceManager {
    private Map<ServiceDescriptor, ServiceInstance> services;

    public ServiceManager() {
        this.services = new ConcurrentHashMap<>();
    }

    public <T> void register(Class<T> interfaceClass, T bean) {
        Method[] publicMethods = ReflectionUtil.getPublicMethods(interfaceClass);
        for (Method method : publicMethods) {
            ServiceInstance instance = new ServiceInstance(bean, method);
            ServiceDescriptor descriptor = ServiceDescriptor.from(interfaceClass, method);
            services.put(descriptor,instance);

            log.info("register service : {} {}", descriptor.getClazz(), descriptor.getMethod());
        }

    }

    public ServiceInstance lookup(Request request) {
        return services.get(request.getServiceDescriptor());
    }
}

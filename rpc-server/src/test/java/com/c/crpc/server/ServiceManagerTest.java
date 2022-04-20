package com.c.crpc.server;

import com.c.crpc.common.utils.ReflectionUtil;
import com.c.crpc.protocol.Request;
import com.c.crpc.protocol.ServiceDescriptor;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class ServiceManagerTest {

    ServiceManager serviceManager;

    @Before
    public void init() {
        serviceManager = new ServiceManager();

        TestInterface bean = new TestBean();
        serviceManager.register(TestInterface.class,bean);
    }

    @Test
    public void register() {
        TestInterface bean = new TestBean();
        serviceManager.register(TestInterface.class,bean);
    }

    @Test
    public void lookup() {
        Method method = ReflectionUtil.getPublicMethods(TestInterface.class)[0];
        Request request = new Request();
        request.setServiceDescriptor(ServiceDescriptor.from(TestInterface.class, method));
        request.setParameters(null);

        ServiceInstance serviceInstance = serviceManager.lookup(request);
        assertNotNull(serviceInstance);
    }
}
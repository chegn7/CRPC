package com.c.crpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Objects;

/**
 * 对服务的描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDescriptor implements Serializable {
    /**
     * 服务所属的类/接口名
     */
    private String clazz;
    /**
     * 服务的方法名
     */
    private String method;
    /**
     *服务方法的返回类型名
     */
    private String returnType;
    /**
     *服务需要的参数类型名
     */
    private String[] parameterTypes;


    public static ServiceDescriptor from(Class clazz, Method method) {
        ServiceDescriptor sd = new ServiceDescriptor();
        sd.setClazz(clazz.getName());
        sd.setMethod(method.getName());
        sd.setReturnType(method.getReturnType().getName());

        Class<?>[] argTypes = method.getParameterTypes();
        String[] parameterTypes = new String[argTypes.length];
        int idx = 0;
        for (Class<?> argType : argTypes) {
            parameterTypes[idx++] = argType.getName();
        }
        sd.setParameterTypes(parameterTypes);
        return sd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ServiceDescriptor that = (ServiceDescriptor) o;

        if (!Objects.equals(clazz, that.clazz)) return false;
        if (!Objects.equals(method, that.method)) return false;
        if (!Objects.equals(returnType, that.returnType)) return false;
        return Arrays.equals(parameterTypes, that.parameterTypes);
    }

    @Override
    public int hashCode() {
        int result = clazz != null ? clazz.hashCode() : 0;
        result = 31 * result + (method != null ? method.hashCode() : 0);
        result = 31 * result + (returnType != null ? returnType.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(parameterTypes);
        return result;
    }

    @Override
    public String toString() {
        return "ServiceDescriptor{" +
                "clazz='" + clazz + '\'' +
                ", method='" + method + '\'' +
                ", returnType='" + returnType + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                '}';
    }
}

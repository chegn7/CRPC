package com.c.crpc.protocol;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 对服务的描述
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDescriptor {
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
}

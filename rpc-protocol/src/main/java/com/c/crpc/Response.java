package com.c.crpc;

import com.c.crpc.enumeration.ResponseEnum;
import lombok.Data;

/**
 * 服务端返回的响应
 */
@Data
public class Response {
    /**
     * 服务端响应状态码
     * 0 成功
     * 非0 失败
     */
    private int code = ResponseEnum.SUCCESS.getCode();
    /**
     * 响应的详细信息
     */
    private String message = ResponseEnum.SUCCESS.getMessage();
    /**
     * 返回的响应数据
     */
    private Object data;
}

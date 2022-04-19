package com.c.crpc;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 网络传输节点
 */
@Data
@AllArgsConstructor
public class Peer {
    private String host;
    private int port;
}

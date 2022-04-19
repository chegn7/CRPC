package com.c.crpc.transport;

import com.c.crpc.common.enumeration.ExceptionEnum;
import com.c.crpc.protocol.Peer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 客户端传输模块的HTTP实现
 * HTTP 短连接，因此close无需处理
 */
@Slf4j
public class HTTPTransportClient implements TransportClient{
    private String url;
    @Override
    public void connect(Peer peer) {
        url = "http://" + peer.getHost() + ":" + peer.getPort();
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");

            conn.connect();

            // 连接建立，通过这个连接传输数据
            IOUtils.copy(data, conn.getOutputStream());

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            } else {
                return conn.getErrorStream();
            }
        } catch (IOException e) {
            String exceptionInfo = ExceptionEnum.TRANSPORT_ERROR.getExceptionMessage()
                    + this.getClass().getName();
            log.error(exceptionInfo);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {

    }
}

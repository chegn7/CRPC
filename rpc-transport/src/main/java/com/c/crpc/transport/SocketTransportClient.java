package com.c.crpc.transport;

import com.c.crpc.protocol.Peer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

@Slf4j
public class SocketTransportClient implements TransportClient {
    Socket socket;

    @Override
    public void connect(Peer peer) {
        try {
            socket = new Socket(peer.getHost(), peer.getPort());
        } catch (IOException e) {
            log.error("socket error : " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public InputStream write(InputStream data) {
        try {
            // 通过socket发送request
            OutputStream os = socket.getOutputStream();
            InputStream is = socket.getInputStream();
            System.out.println(data);
            System.out.println("---------------------------------");
            System.out.println(data);
//            os.write(IOUtils.readFully(data, data.available()));
//            os.flush();
            IOUtils.copy(data,os);
            return is;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                log.error("socket close error : " + e.getMessage(), e);
                throw new RuntimeException(e);
            }
        }

    }
}

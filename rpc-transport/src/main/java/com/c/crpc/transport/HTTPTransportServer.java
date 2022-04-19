package com.c.crpc.transport;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 基于jetty实现的HTTP传输的传输服务端
 * 基于servlet接收请求
 */
@Slf4j
public class HTTPTransportServer implements TransportServer{
    private RequestHandler handler;
    private Server server;

    @Override
    public void init(int port, RequestHandler handler) {
        this.handler = handler;
        server = new Server(port);

        ServletContextHandler contextHandler = new ServletContextHandler();
        server.setHandler(contextHandler);

        ServletHolder holder = new ServletHolder(new RequestServlet());
        contextHandler.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("client connect.");

            InputStream inputStream = req.getInputStream();
            OutputStream outputStream = resp.getOutputStream();

            if (handler != null) {
                handler.onRequest(inputStream, outputStream);
            }

            outputStream.flush();
        }
    }
}

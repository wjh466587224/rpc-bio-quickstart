package org.example.v2.proxy.server;

import org.example.v2.handler.ProcessorHandler;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//spring容器启动之后，会发布一个ContextRefreshedEvent事件
@Component
public class SocketServerInitial implements ApplicationListener<ContextRefreshedEvent> {

    private final ExecutorService executorService= Executors.newCachedThreadPool();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket= null;
        try {
            serverSocket  = new ServerSocket(8888);

            while (true){
                Socket socket = serverSocket.accept();
                executorService.execute(new ProcessorHandler(socket));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket!=null){
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

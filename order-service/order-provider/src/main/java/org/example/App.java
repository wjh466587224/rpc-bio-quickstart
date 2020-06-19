package org.example;


import org.example.service.IOrderService;
import org.example.v1.proxy.server.RpcProxyServer;
import org.example.v1.service.impl.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 *
 */
@Configuration
@ComponentScan("org.example.v2")
public class App 
{
    public static void main( String[] args )
    {

        //V1
//        IOrderService orderService = new OrderServiceImpl();
//
//        RpcProxyServer rpcProxyServer = new RpcProxyServer();
//        rpcProxyServer.publisher(orderService,8080);

        //V2
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(App.class);

    }
}

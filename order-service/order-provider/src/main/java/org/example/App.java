package org.example;


import org.example.service.IOrderService;
import org.example.v1.proxy.server.RpcProxyServer;
import org.example.v1.service.impl.OrderServiceImpl;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        IOrderService orderService = new OrderServiceImpl();

        RpcProxyServer rpcProxyServer = new RpcProxyServer();
        rpcProxyServer.publisher(orderService,8080);
    }
}

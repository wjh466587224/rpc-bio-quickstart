package org.example;

import org.example.service.IOrderService;
import org.example.v1.client.proxy.RpcProxyClient;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        RpcProxyClient rpcProxyClient = new RpcProxyClient();

        IOrderService orderService = rpcProxyClient.clientProxy(IOrderService.class, "localhost", 8080);

        System.out.println(orderService.queryOrderList());
        System.out.println(orderService.orderById("a"));
    }
}

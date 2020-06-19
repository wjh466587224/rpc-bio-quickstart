package org.example;

import org.example.service.IOrderService;
import org.example.service.IProductService;
import org.example.v1.client.proxy.RpcProxyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {
    public static void main( String[] args ) {
        //V2
        SpringApplication.run(App.class,args);
        //V1
//        RpcProxyClient rpcProxyClient = new RpcProxyClient();
//
//        IOrderService orderService = rpcProxyClient.clientProxy(IOrderService.class, "localhost", 8080);
//
//        System.out.println(orderService.queryOrderList());
//        System.out.println(orderService.orderById("a"));
//
//        IProductService productService = rpcProxyClient.clientProxy(IProductService.class, "localhost", 8080);
//
//        System.out.println(productService.queryProductList());
    }
}

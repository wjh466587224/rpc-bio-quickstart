package org.example.v2.controller;

import org.example.service.IOrderService;
import org.example.service.IProductService;
import org.example.v2.annotation.WjhReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    @WjhReference
    private IOrderService orderService;
    @WjhReference
    private IProductService productService;

    @GetMapping("/test")
    public String test(){
        return orderService.queryOrderList();
    }
    @GetMapping("/get")
    public String get(){
        return productService.queryProductList();
    }
}

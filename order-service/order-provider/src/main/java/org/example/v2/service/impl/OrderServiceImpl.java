package org.example.v2.service.impl;

import org.example.service.IOrderService;
import org.example.v2.annotation.WjhRemoteService;

@WjhRemoteService
public class OrderServiceImpl implements IOrderService {


    @Override
    public String queryOrderList() {
        return "EXECUTE QUERYORDERLIST METHOD";
    }

    @Override
    public String orderById(String id) {
        return "EXECUTE ORDER_BY_ID METHOD";
    }
}

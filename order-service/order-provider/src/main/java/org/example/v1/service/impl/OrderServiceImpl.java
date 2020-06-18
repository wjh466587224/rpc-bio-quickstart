package org.example.v1.service.impl;

import org.example.service.IOrderService;

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

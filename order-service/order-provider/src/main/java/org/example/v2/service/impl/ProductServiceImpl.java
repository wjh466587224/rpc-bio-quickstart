package org.example.v2.service.impl;

import org.example.service.IProductService;
import org.example.v2.annotation.WjhRemoteService;

@WjhRemoteService
public class ProductServiceImpl implements IProductService {

    @Override
    public String queryProductList() {
        return "PRODUCT";
    }
}

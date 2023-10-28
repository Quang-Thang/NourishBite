package com.example.nourishbite.api;

import com.example.nourishbite.service.ProductService;

public class ProductRepository {

    public static ProductService getProductService(){
        return APIClient.getClient().create(ProductService.class);
    }

}

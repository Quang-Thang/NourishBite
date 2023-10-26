package com.example.nourishbite.service;

import com.example.nourishbite.model.Product;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ProductService {
    String PRODUCT = "products";

    @GET(PRODUCT)
    Call<List<Product>> getAllProduct();

    @GET(PRODUCT + "/{id}")
    Call<Product> getProductById(@Path("id") Object id);

}

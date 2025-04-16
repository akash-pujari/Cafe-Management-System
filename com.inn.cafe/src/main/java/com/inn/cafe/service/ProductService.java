package com.inn.cafe.service;

import com.inn.cafe.pojo.Product;
import com.inn.cafe.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface ProductService {
    ResponseEntity<String> addNewProduct(Map<String, String> requestBody);

    ResponseEntity<List<ProductWrapper>> getAllProduct();

    ResponseEntity<String> updateProduct(Map<String, String> requestBody);

    ResponseEntity<String> removeProduct(Integer id);

    ResponseEntity<List<ProductWrapper>> getProductByCategoryId(Integer id);

    ResponseEntity<ProductWrapper> getProductById(Integer id);
}

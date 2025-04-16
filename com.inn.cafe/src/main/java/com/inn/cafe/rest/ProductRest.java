package com.inn.cafe.rest;

import com.inn.cafe.pojo.Product;
import com.inn.cafe.wrapper.ProductWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/product")
public interface ProductRest {

    @PostMapping("/add")
    ResponseEntity<String> addNewProduct(@RequestBody(required = true) Map<String, String> requestBody);

    @GetMapping("/getAllProduct")
    ResponseEntity<List<ProductWrapper>> getAllProduct();

    @PutMapping("/updateProduct")
    ResponseEntity<String> updateProduct(@RequestBody(required = true) Map<String, String> requestBody);

    @PutMapping("/removeProduct/{id}")
    ResponseEntity<String> removeProduct(@PathVariable(required = true) Integer id);

    @GetMapping(path = "getProductByCategoryId/{id}")
    ResponseEntity<List<ProductWrapper>> getProductByCategoryId(@PathVariable(required = true) Integer id);

    @GetMapping("/getProductById/{id}")
    ResponseEntity<ProductWrapper> getProductById(@PathVariable("id") Integer id);
}

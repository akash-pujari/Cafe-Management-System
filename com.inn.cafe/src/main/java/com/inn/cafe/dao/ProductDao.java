package com.inn.cafe.dao;

import com.inn.cafe.pojo.Product;
import com.inn.cafe.wrapper.ProductWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name, p.status, p.category.id, p.description, p.price) FROM Product p JOIN p.category c")
    List<ProductWrapper> getAllProduct();
}

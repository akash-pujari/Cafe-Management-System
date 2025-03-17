package com.inn.cafe.dao;

import com.inn.cafe.pojo.Product;
import com.inn.cafe.wrapper.ProductWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name, p.status, p.category.id, p.description, p.price, p.category_name) FROM Product p JOIN p.category c")
    List<ProductWrapper> getAllProduct();

    @Query("SELECT p FROM Product p  WHERE p.id=:id")
    Product getProductById(Integer id);

    @Query("DELETE FROM Product p WHERE p.id=:id")
    @Transactional
    @Modifying
    void removeProduct(Integer id);
}

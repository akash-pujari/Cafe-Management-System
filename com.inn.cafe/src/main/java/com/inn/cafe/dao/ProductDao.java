package com.inn.cafe.dao;

import com.inn.cafe.pojo.Product;
import com.inn.cafe.wrapper.ProductWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductDao extends JpaRepository<Product, Integer> {
    @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name, p.status, p.category.id, p.description, p.price, p.category_name) FROM Product p JOIN p.category c")
    List<ProductWrapper> getAllProduct();

    @Query("SELECT p FROM Product p  WHERE p.id=:id")
    Product getProductById(Integer id);

    @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name, p.status, p.category.id, p.description, p.price, p.category_name) FROM Product p WHERE p.id=:id")
    ProductWrapper getProductByItsId(Integer id);

    @Query("DELETE FROM Product p WHERE p.id=:id")
    @Transactional
    @Modifying
    void removeProduct(Integer id);

   /* @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name, p.status, p.category.id, p.description, p.price, p.category_name) FROM Product p WHERE p.category.id = :id AND p.status = 'true'")
    List<ProductWrapper> getProductByCategoryId(@Param("id") Integer id);*/

    /*@Query(value = "SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id,p.name) from Product p where p.category_fk=:id and p.status='true'", nativeQuery = true)
    List<ProductWrapper> getProductByCategoryId(@Param("id") Integer id);*/

   /* @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name) FROM Product p WHERE p.category.id = :id AND p.status = 'true'")
    List<ProductWrapper> getProductByCategoryId(@Param("id") Integer id);*/
/*
    @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id,p.name) from Product p where p.category.id=:id AND p.status='true")
    List<ProductWrapper> getProductByCategoryId(@Param("id")Integer id);*/

    @Query("SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id, p.name) FROM Product p WHERE p.category.id = :id AND p.status = 'true'")
    List<ProductWrapper> getProductByCategoryId(@Param("id") Integer id);


    //SELECT new com.inn.cafe.wrapper.ProductWrapper(p.id,p.name) from Product p where p.category_fk=:id and p.status='true'

}

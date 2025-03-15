package com.inn.cafe.dao;

import com.inn.cafe.pojo.Category;
import com.inn.cafe.wrapper.CategoryWrapper;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryDao extends JpaRepository<Category, Integer> {

    @Query("SELECT new com.inn.cafe.wrapper.CategoryWrapper(c.id,c.name) FROM Category c")
    List<CategoryWrapper> getAllCategory();

    @Query("SELECT c FROM Category c WHERE c.name = :name")
    Category getCategoryByName(@Param("name") String name);

    @Query("SELECT c FROM Category c WHERE c.id = :id")
    Category getCategoryById(@Param("id") Integer id);


    @Modifying
    @Transactional
    @Query("DELETE FROM Category c WHERE c.id = :id AND c.name = :name")
    void deleteCategory(@Param("id") Integer id, @Param("name") String name);

}

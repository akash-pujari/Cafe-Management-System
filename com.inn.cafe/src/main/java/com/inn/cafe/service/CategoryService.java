package com.inn.cafe.service;

import com.inn.cafe.wrapper.CategoryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface CategoryService {
    ResponseEntity<String> addNewCategory(Map<String, String> requestBody);

    ResponseEntity<String> removeCategory(Map<String, String> requestBody);

    ResponseEntity<String> updateCategoryName(Map<String, String> requestBody);

    ResponseEntity<List<CategoryWrapper>> getAllCategory(String filterValue);
}

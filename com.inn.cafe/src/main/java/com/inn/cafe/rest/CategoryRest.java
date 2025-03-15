package com.inn.cafe.rest;

import com.inn.cafe.wrapper.CategoryWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/category")
public interface CategoryRest {

    @PostMapping(path = "/add")
    ResponseEntity<String> addNewCategory(@RequestBody(required = true) Map<String, String> requestBody);

    @DeleteMapping(path = "/remove")
    ResponseEntity<String> removeCategory(@RequestBody(required = true) Map<String, String> requestBody);

    @PutMapping(path = "/update")
    ResponseEntity<String> updateCategoryName(@RequestBody(required = true) Map<String, String> requestBody);


    @GetMapping("/getAllCategory")
    ResponseEntity<List<CategoryWrapper>> getAllCategory(@RequestBody(required = false) String filterValue);
}

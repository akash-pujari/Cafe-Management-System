package com.inn.cafe.restImpl;

import com.inn.cafe.rest.CategoryRest;
import com.inn.cafe.service.CategoryService;
import com.inn.cafe.utils.CafeUtils;
import com.inn.cafe.wrapper.CategoryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.inn.cafe.constants.CafeConstants.SOMETHING_WENT_WRONG;

@RestController
public class CategoryRestImpl implements CategoryRest {


    @Autowired
    private CategoryService categoryService;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestBody) {
        try {
            return categoryService.addNewCategory(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> removeCategory(Map<String, String> requestBody) {
        try {
            return categoryService.removeCategory(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategoryName(Map<String, String> requestBody) {
        try {
            return categoryService.updateCategoryName(requestBody);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<CategoryWrapper>> getAllCategory(String filterValue) {
        try {
            return categoryService.getAllCategory(filterValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getCategoryResponse(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}


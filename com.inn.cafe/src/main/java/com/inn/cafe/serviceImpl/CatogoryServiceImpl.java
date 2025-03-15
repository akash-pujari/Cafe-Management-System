package com.inn.cafe.serviceImpl;

import com.inn.cafe.dao.CategoryDao;
import com.inn.cafe.jwt.JwtAuthenticationFilter;
import com.inn.cafe.pojo.Category;
import com.inn.cafe.service.CategoryService;
import com.inn.cafe.utils.CafeUtils;
import com.inn.cafe.wrapper.CategoryWrapper;
import lombok.Setter;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.inn.cafe.constants.CafeConstants.SOMETHING_WENT_WRONG;

@Setter
@Service
public class CatogoryServiceImpl implements CategoryService {
    private static final Logger log = LoggerFactory.getLogger(CatogoryServiceImpl.class);
    @Autowired
    CategoryDao categoryDao;

    @Autowired
    JwtAuthenticationFilter filter;

    @Override
    public ResponseEntity<String> addNewCategory(Map<String, String> requestBody) {

        try {
            if (filter.isAdmin()) {
                Category category = categoryDao.getCategoryByName(requestBody.get("name"));
                if (category == null) {
                    category = new Category();
                    category.setName(requestBody.get("name"));
                    categoryDao.save(category);
                    return CafeUtils.getResponse("Category added successfully!", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponse("Category exists!!. Please add other category of your interest", HttpStatus.OK);
                }
            } else {
                return CafeUtils.getResponse("Only admin can add category", HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @Override
    public ResponseEntity<String> removeCategory(Map<String, String> requestBody) {
        try {
            Category category = categoryDao.getCategoryByName(requestBody.get("name"));
            if (filter.isAdmin()) {
                if (category != null && !category.getName().isEmpty()) {
                    categoryDao.deleteCategory(Integer.parseInt(requestBody.get("id")), requestBody.get("name"));
                    return CafeUtils.getResponse("Category removed successfully!", HttpStatus.OK);
                } else {
                    return CafeUtils.getResponse("Category not found", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponse("Only admin can remove category", HttpStatus.FORBIDDEN);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> updateCategoryName(Map<String, String> requestBody) {
        try {
            Category category = categoryDao.getCategoryById(Integer.parseInt(requestBody.get("id")));
            if (filter.isAdmin()) {
                if (category != null && !category.getName().isEmpty() && Integer.parseInt(requestBody.get("id")) == category.getId() && !category.getName().isEmpty()) {
                    category.setName(requestBody.get("name"));
                    categoryDao.save(category);
                    return CafeUtils.getResponse("Category name successfully updated to " + category.getName() + ".", HttpStatus.NO_CONTENT);
                } else {
                    return CafeUtils.getResponse("No such category registered with category id " + requestBody.get("id") + ".", HttpStatus.BAD_REQUEST);
                }
            } else {
                return CafeUtils.getResponse("Only admin can update category name", HttpStatus.FORBIDDEN);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getResponse(SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<List<CategoryWrapper>> getAllCategory(String filterValue) {
        try {
            List<CategoryWrapper> category = categoryDao.getAllCategory();
            if (!Strings.isEmpty(filterValue) && filterValue.equalsIgnoreCase("true")) {
                log.info("Inside filter getAllCategory");
                return CafeUtils.getCategoryResponse(category, HttpStatus.OK);
            } else {
                return CafeUtils.getCategoryResponse(category, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CafeUtils.getCategoryResponse(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}


package com.inn.cafe.utils;

import com.inn.cafe.wrapper.CategoryWrapper;
import com.inn.cafe.wrapper.UserWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CafeUtils {

    private CafeUtils() {

    }

    public static ResponseEntity<String> getResponse(String message, HttpStatus httpStatus) {
        return new ResponseEntity<>("{\"message\" : " + "\"" + message + "\"" + "}", httpStatus);
    }

    public static ResponseEntity<List<UserWrapper>> getResponse(List<UserWrapper> user, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ArrayList<>(user), httpStatus);
    }

    public static ResponseEntity<List<CategoryWrapper>> getCategoryResponse(List<CategoryWrapper> user, HttpStatus httpStatus) {
        return new ResponseEntity<>(new ArrayList<>(user), httpStatus);
    }
}

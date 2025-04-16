package com.inn.cafe.utils;

import com.google.gson.Gson;
import com.inn.cafe.wrapper.CategoryWrapper;
import com.inn.cafe.wrapper.UserWrapper;
import org.apache.logging.log4j.util.Strings;
import org.json.JSONArray;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

    public static String getUUID() {
        Date data = new Date();
        long time = data.getTime();
        return "Bill-" + time;
    }

    public static JSONArray getJsonArrayFromString(String productDetail) {
        return new JSONArray(productDetail);
    }

    /*public static Map<String,Object> getMapFromJson(String data){
        if(!Strings.isEmpty(data)){
            return new Gson().fromJson();
        }
    }*/
}

package com.inn.cafe.service;

import com.inn.cafe.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestBody);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<String> checkToken();

    ResponseEntity<String> changePassword(Map<String,String> requestBody);
    ResponseEntity<List<UserWrapper>> getAllUser();

    ResponseEntity<String> updateUserStatus(HttpServletRequest httpServletRequest, Map<String,String> requestBody);
}

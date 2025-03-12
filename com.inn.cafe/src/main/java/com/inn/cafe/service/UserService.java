package com.inn.cafe.service;

import com.inn.cafe.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;

public interface UserService {
    ResponseEntity<String> signUp(Map<String, String> requestBody);

    ResponseEntity<String> login(Map<String, String> requestMap);

    ResponseEntity<List<UserWrapper>> getAllUser(HttpServletRequest httpServletRequest);

    ResponseEntity<String> updateUserStatus(HttpServletRequest httpServletRequest,Map<String,String> requestBody);
}

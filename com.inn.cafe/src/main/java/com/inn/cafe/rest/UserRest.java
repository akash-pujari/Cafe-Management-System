package com.inn.cafe.rest;

import com.inn.cafe.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@RequestMapping("/user")
public interface UserRest {
    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestBody);

    @PostMapping(path = "/login")
    ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping("/hello")
    ResponseEntity<String> hello();

    @GetMapping(path = "/get")
    ResponseEntity<List<UserWrapper>> getAllUser(HttpServletRequest httpServletRequest);

    @PostMapping(path="updateUserStatus")
    ResponseEntity<String> updateUserStatus(HttpServletRequest httpServletRequest,@RequestBody(required = true)Map<String,String> requestBody);
}

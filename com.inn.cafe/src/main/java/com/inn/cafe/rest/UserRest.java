package com.inn.cafe.rest;

import com.inn.cafe.wrapper.UserWrapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RequestMapping("/user")
public interface UserRest {
    @PostMapping("/signup")
    ResponseEntity<String> signUp(@RequestBody(required = true) Map<String, String> requestBody);

    @PostMapping(path = "/login")
    ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMap);

    @GetMapping("/checkToken")
    ResponseEntity<String> checkToken();

    @GetMapping(path = "/getAllUsers")
    ResponseEntity<List<UserWrapper>> getAllUser();

    @PostMapping(path ="changePassword")
    ResponseEntity<String> changePassword(@RequestBody(required = true) Map<String,String> requestBody);

    @PostMapping(path="updateUserStatus")
    ResponseEntity<String> updateUserStatus(HttpServletRequest httpServletRequest,@RequestBody(required = true)Map<String,String> requestBody);
}

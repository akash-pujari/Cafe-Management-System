package com.inn.cafe.jwt;

import org.junit.jupiter.api.Test;

class JwtUtilTest {
    JwtUtil jwtUtil = new JwtUtil();

    @Test
    public void testGenerateToken() {
        jwtUtil.generateToken("Akash", "Akash");
    }
}
package com.carlos.ingtest.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


@Service
public class JwtService {
    @Value("${applicationSecurity.jwt.secret-key}")
    public String secretKey;

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String userName = JWTUtils.extractUserName(token, secretKey);
        return userName.equals(userDetails.getUsername()) && !JWTUtils.isTokenExpired(token, secretKey);
    }

}
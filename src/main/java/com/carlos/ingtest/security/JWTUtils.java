package com.carlos.ingtest.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

public class JWTUtils {

    private JWTUtils() {
    }

    public static Key getSignKey(String secretKey) {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public static Boolean isTokenExpired(String token, String secretKey) {
        return extractExpiration(token, secretKey).before(new Date());
    }

    public static String extractUserName(String token, String secretKey) {
        return extractClaim(token, Claims::getSubject, secretKey);
    }

    private static Date extractExpiration(String token, String secretKey) {
        return extractClaim(token, Claims::getExpiration, secretKey);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimResolver, String secretKey) {
        final Claims claims = extractAllClaims(token, secretKey);
        return claimResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token, String secretKey) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey(secretKey))
                .build().parseClaimsJws(token).getBody();
    }


}

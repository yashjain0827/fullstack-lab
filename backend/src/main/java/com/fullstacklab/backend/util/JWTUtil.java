package com.fullstacklab.backend.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
@Component
public class JWTUtil {

    private static  final String SECRET_KEY = "NGIyZGE0ODU0MjQ1M2E3ZDYxMjM0NTY3ODkwYWJjZGVmOTg3NjU0MzIxMGRjYmE5ODc2NTQzMjEwZmVkY2Jh";
    private static  final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes(StandardCharsets.UTF_8));


    public String generateToken (String username , long expireTime){
    return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(new Date())
            .setExpiration(new Date(System.currentTimeMillis() + expireTime*60*1000))
            .signWith(key, SignatureAlgorithm.HS256)
            .compact();
    }


    public String extractUsername(String token){

        try {
            return Jwts.parser()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            // Handle the exception (e.g., log it, throw a custom exception, etc.)
            return null; // or throw an exception
        }
    }




}

package com.example.kavana_resume_system.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    // Your fixed secret key (at least 32 chars for HS256)
    private static final String SECRET = "c29tZVN1cGVyU2VjcmV0S2V5MTIzITQ1Njc4OTAyMzQ1Njc4"; 

    // Convert secret string to Key
    private static final Key SECRET_KEY = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS256.getJcaName());

    // Token expiration time: 24 hours
    private static final long EXPIRATION_TIME = 24 * 60 * 60 * 1000;

    // Generate JWT token
    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();
    }

    // Extract email from token
    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
    public String extractUsername(String token) {
        return extractEmail(token);
    }

}

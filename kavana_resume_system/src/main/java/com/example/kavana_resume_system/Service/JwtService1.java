package com.example.kavana_resume_system.Service;

public interface JwtService1 {
    String generateToken(String email);
    String extractUsername(String token); // ✅ add this
}


package com.example.kavana_resume_system.Service;

import com.example.kavana_resume_system.DTO.RegisterRequest;
import com.example.kavana_resume_system.DTO.LoginRequest;
import com.example.kavana_resume_system.DTO.AuthResponse;



public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}

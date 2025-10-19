package com.example.kavana_resume_system.ServiceImpl;

import com.example.kavana_resume_system.DTO.RegisterRequest;
import com.example.kavana_resume_system.DTO.LoginRequest;
import com.example.kavana_resume_system.DTO.AuthResponse;
import com.example.kavana_resume_system.Entity.User;
import com.example.kavana_resume_system.Repository.UserRepository;
import com.example.kavana_resume_system.Service.AuthService;
import com.example.kavana_resume_system.Service.JwtService;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private JwtService jwtService;

    
    @Override
    public AuthResponse register(RegisterRequest request) {

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            return new AuthResponse(null, "Email already registered", request.getEmail());
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole("USER"); // add default role

        userRepository.save(user);

        // Normally generate JWT token here
        String token =  jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "User registered successfully", user.getEmail());
    }

    @Override
    public AuthResponse login(LoginRequest request) {

        // Find user by email
        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());

        if (userOpt.isEmpty()) {
            return new AuthResponse(null, "Invalid email or password", request.getEmail());
        }

        User user = userOpt.get();

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return new AuthResponse(null, "Invalid email or password", request.getEmail());
        }

        // Normally generate JWT token here
        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponse(token, "Login successful", user.getEmail());
    }
}

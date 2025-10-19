package com.example.kavana_resume_system.Controller;

import com.example.kavana_resume_system.DTO.AuthResponse;
import com.example.kavana_resume_system.DTO.LoginRequest;
import com.example.kavana_resume_system.DTO.RegisterRequest;
import com.example.kavana_resume_system.Entity.User;
import com.example.kavana_resume_system.Repository.UserRepository;
import com.example.kavana_resume_system.Service.AuthService;
import com.example.kavana_resume_system.Service.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;


    @PostMapping(value = "/register", consumes = "application/json")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
    @GetMapping("/profile")
    public ResponseEntity<User> getProfile(@RequestHeader("Authorization") String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return ResponseEntity.status(401).build();
        }

        String token = authHeader.substring(7);
        String email = jwtService.extractEmail(token);  // use your JwtService

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return ResponseEntity.ok(user);
    }

    
    
}

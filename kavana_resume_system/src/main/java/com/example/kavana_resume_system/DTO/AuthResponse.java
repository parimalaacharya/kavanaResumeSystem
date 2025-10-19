package com.example.kavana_resume_system.DTO;

public class AuthResponse {

    private String token;
    private String message;
    private String email;

    // ✅ No-args constructor
    public AuthResponse() {}

    // ✅ All-args constructor
    public AuthResponse(String token, String message, String email) {
        this.token = token;
        this.message = message;
        this.email = email;
    }

    // Getters and Setters
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

	



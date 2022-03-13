package com.example.covenant.journey.api.models.auth;

import com.example.covenant.journey.api.models.user.UserResponse;

public class AuthResponse {

    private UserResponse user;

    private String token;

    public AuthResponse(UserResponse user, String token) {
        this.user = user;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}

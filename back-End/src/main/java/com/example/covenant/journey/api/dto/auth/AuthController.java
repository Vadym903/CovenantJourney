package com.example.covenant.journey.api.dto.auth;

import com.example.covenant.journey.api.dto.user.UserRequest;
import com.example.covenant.journey.api.dto.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = AuthController.URL)
public class AuthController {

    public static final String URL = "/auth";

    @Autowired
    private AuthApiService authApiService;

    @PostMapping("/registration")
    public void registerUser(@RequestBody @Valid UserRequest request) {
        authApiService.registerUser(request);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody @Valid AuthRequest request) {
        return authApiService.login(request);
    }

    @GetMapping(value = "/current")
    public UserResponse getCurrentUser() {
        return authApiService.getCurrentUser();
    }

}
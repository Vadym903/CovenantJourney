package com.example.covenant.journey.api.dto.auth;

import com.example.covenant.journey.api.dto.user.UserRequest;
import com.example.covenant.journey.api.dto.user.UserResponse;
import com.example.covenant.journey.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
@RequestMapping(value = AuthController.URL)
public class AuthController {

    public static final String URL = "/auth";

    @Autowired
    private AuthApiService authApiService;

    @Autowired
    private JwtProvider jwtProvider;

    @PostMapping("/registration")
    @ResponseBody
    public AuthResponse registerUser(@RequestBody @Valid UserRequest request) {
        UserResponse user = authApiService.registerUser(request);
        return new AuthResponse(user, jwtProvider.generateToken(user.getLogin()));
    }

    @PostMapping("/login")
    @ResponseBody
    public AuthResponse login(@RequestBody @Valid AuthRequest request) {
        return authApiService.login(request);
    }

    @GetMapping(value = "/current")
    @ResponseBody
    public UserResponse getCurrentUser() {
        return authApiService.getCurrentUser();
    }

}
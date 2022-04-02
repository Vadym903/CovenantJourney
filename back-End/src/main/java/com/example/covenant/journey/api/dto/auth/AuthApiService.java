package com.example.covenant.journey.api.dto.auth;

import com.example.covenant.journey.api.dto.user.UserConverter;
import com.example.covenant.journey.api.dto.user.UserRequest;
import com.example.covenant.journey.api.dto.user.UserResponse;
import com.example.covenant.journey.model.user.User;
import com.example.covenant.journey.security.jwt.JwtProvider;
import com.example.covenant.journey.services.user.CurrentUserService;
import com.example.covenant.journey.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthApiService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private JwtProvider jwtProvider;

    public UserResponse registerUser(UserRequest user) {
        if (userService.findUserByLogin(user.getLogin()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
        User savedUser = userService.create(userConverter.convertRequestToEntity(user));
        return new UserResponse(savedUser);
    }

    public AuthResponse login(AuthRequest request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (user != null) {
            String token = jwtProvider.generateToken(user.getLogin());
            return new AuthResponse(userConverter.convertEntityToResponse(user), token);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    public UserResponse getCurrentUser() {
        User user = currentUserService.getCurrentUser();
        if (user != null) {
            return userConverter.convertEntityToResponse(user);
        }
        return null;
    }

}

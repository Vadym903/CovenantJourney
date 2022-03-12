package com.example.covenant.journey.api.models.auth;

import com.example.covenant.journey.api.models.user.UserConverter;
import com.example.covenant.journey.api.models.user.UserRequest;
import com.example.covenant.journey.api.models.user.UserResponse;
import com.example.covenant.journey.models.user.UserEntity;
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

    public void registerUser(UserRequest user) {
        if (userService.findUserByLogin(user.getLogin()) == null) {
            userService.create(userConverter.convertRequestToEntity(user));
            return;
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT); // TODO Handle on front
    }

    public AuthResponse login(AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (userEntity != null) {
            String token = jwtProvider.generateToken(userEntity.getLogin());
            return new AuthResponse(userConverter.convertEntityToResponse(userEntity), token);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    public UserResponse getCurrentUser() {
        UserEntity userEntity = currentUserService.getCurrentUser();
        if (userEntity != null) {
            return userConverter.convertEntityToResponse(userEntity);
        }
        return null;
    }

}

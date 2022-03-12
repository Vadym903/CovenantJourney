package com.example.covenant.journey.api.models.user;

import com.example.covenant.journey.models.user.UserEntity;
import com.example.covenant.journey.services.photo.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private PhotoService photoService;

    public UserEntity convertRequestToEntity(UserRequest userRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequest.getEmail());
        userEntity.setFirstName(userRequest.getFirstName());
        userEntity.setLastName(userRequest.getSecondName());
        userEntity.setPassword(userRequest.getPassword());
        userEntity.setLogin(userRequest.getLogin());
        userEntity.setDescription(userRequest.getDescription());
        if (userRequest.getPhotoId() != null) {
            userEntity.setPhoto(photoService.getShorterPhotoEntityById(userRequest.getPhotoId()));
        }
        return userEntity;
    }

    public UserResponse convertEntityToResponse(UserEntity user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFirstName(user.getFirstName());
        response.setSecondName(user.getLastName());
        response.setLogin(user.getLogin());
        response.setRole(user.getRole());
        response.setDescription(user.getDescription());
        if (user.getPhoto() != null) {
            response.setPhotoId(user.getPhoto().getId());
        }
        return response;
    }

}

package com.example.covenant.journey.api.dto.user;

import com.example.covenant.journey.model.user.User;
import com.example.covenant.journey.services.photo.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ImageService imageService;

    public User convertRequestToEntity(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setFullName(userRequest.getFullName());
        user.setPassword(userRequest.getPassword());
        user.setLogin(userRequest.getLogin());
        user.setDescription(userRequest.getDescription());
//        if (userRequest.getPhotoId() != null) {
//            user.setPhoto(imageService.getShorterPhotoEntityById(userRequest.getPhotoId()));
//        }
        return user;
    }

    public UserResponse convertEntityToResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setFullName(user.getFullName());
        response.setLogin(user.getLogin());
        response.setRole(user.getRole());
        response.setDescription(user.getDescription());
//        if (user.getPhoto() != null) {
//            response.setPhotoId(user.getPhoto().getId());
//        }
        return response;
    }

}

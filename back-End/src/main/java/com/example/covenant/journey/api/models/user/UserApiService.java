package com.example.covenant.journey.api.models.user;

import com.example.covenant.journey.api.filters.models.EntityFilterSpecificationsBuilder;
import com.example.covenant.journey.api.models.general.AbstractApiService;
import com.example.covenant.journey.models.user.UserEntity;
import com.example.covenant.journey.services.photo.PhotoService;
import com.example.covenant.journey.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserApiService extends AbstractApiService<
        UserEntity,
        UserResponse,
        UserRequest,
        UserService> {

    @Autowired
    private PhotoService photoService;


    @Autowired
    private UserConverter userConverter;

    @Override
    protected EntityFilterSpecificationsBuilder<UserEntity> getSpecificationBuilder() {
        return null;
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        UserEntity userEntity = service.findUserByLogin(request.getLogin());
        if (id == userEntity.getId()) {
            return super.update(id, request);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT); // TODO Handle on front
    }

    @Override
    protected UserEntity convertRequestToEntity(UserRequest request) {
        return userConverter.convertRequestToEntity(request);
    }

    @Override
    protected void copyProperties(UserRequest request, UserEntity entity) {
        entity.setLogin(request.getLogin());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getSecondName());
        entity.setEmail(request.getEmail());
        entity.setDescription(request.getDescription());
        if (request.getPhotoId() != null) {
            entity.setPhoto(photoService.getShorterPhotoEntityById(request.getPhotoId()));
        }
    }

    @Override
    protected UserResponse convertEntityToResponse(UserEntity entity, Optional<String> expand) {
        return userConverter.convertEntityToResponse(entity);
    }
}

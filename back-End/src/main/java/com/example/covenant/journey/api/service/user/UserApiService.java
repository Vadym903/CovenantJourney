package com.example.covenant.journey.api.service.user;

import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.api.dto.general.AbstractApiService;
import com.example.covenant.journey.api.dto.user.UserConverter;
import com.example.covenant.journey.api.dto.user.UserRequest;
import com.example.covenant.journey.api.dto.user.UserResponse;
import com.example.covenant.journey.model.user.User;
import com.example.covenant.journey.services.photo.ImageService;
import com.example.covenant.journey.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserApiService extends AbstractApiService<
        User,
		UserResponse,
		UserRequest,
        UserService> {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserConverter userConverter;

    @Override
    protected FilteringSpecificationsBuilder<User> getSpecificationBuilder() {
        return null;
    }

    @Override
    public UserResponse update(Long id, UserRequest request) {
        User user = service.findUserByLogin(request.getLogin());
        if (id.equals(user.getId())) {
            return super.update(id, request);
        }
        throw new ResponseStatusException(HttpStatus.CONFLICT);
    }

    @Override
    protected User convertRequestToEntity(UserRequest request) {
        return userConverter.convertRequestToEntity(request);
    }

    @Override
    protected void copyProperties(UserRequest request, User entity) {
        entity.setLogin(request.getLogin());
        entity.setFullName(request.getFullName());
        entity.setEmail(request.getEmail());
        entity.setDescription(request.getDescription());

    }

    @Override
    protected UserResponse convertEntityToResponse(User entity, Optional<String> expand) {
        return userConverter.convertEntityToResponse(entity);
    }
}

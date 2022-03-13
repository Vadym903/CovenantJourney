package com.example.covenant.journey.api.models.user;

import com.example.covenant.journey.api.models.general.AbstractCRUDController;
import com.example.covenant.journey.models.user.UserEntity;
import com.example.covenant.journey.services.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserController.URL)
public class UserController extends AbstractCRUDController<
        UserResponse,
        UserEntity,
        UserRequest,
        UserService,
        UserApiService> {

    public static final String URL = "/user";
}

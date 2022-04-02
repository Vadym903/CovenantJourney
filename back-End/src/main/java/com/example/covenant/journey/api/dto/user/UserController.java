package com.example.covenant.journey.api.dto.user;

import com.example.covenant.journey.api.dto.general.AbstractCRUDController;
import com.example.covenant.journey.api.service.user.UserApiService;
import com.example.covenant.journey.models.user.User;
import com.example.covenant.journey.services.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = UserController.URL)
public class UserController extends AbstractCRUDController<
        UserResponse,
		User,
        UserRequest,
        UserService,
		UserApiService> {

    public static final String URL = "/user";
}

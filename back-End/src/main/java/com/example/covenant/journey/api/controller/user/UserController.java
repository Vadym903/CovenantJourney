package com.example.covenant.journey.api.controller.user;

import com.example.covenant.journey.api.dto.general.AbstractController;
import com.example.covenant.journey.api.dto.user.UserRequest;
import com.example.covenant.journey.api.dto.user.UserResponse;
import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.api.shared.APIConstants;
import com.example.covenant.journey.model.user.User;
import com.example.covenant.journey.services.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserController.API_URL)
public class UserController extends AbstractController<
		UserResponse,
		User,
		UserRequest,
        UserService> {

    public static final String API_URL = APIConstants.USER_API;

	@Override
	public UserResponse convertEntityToResponse(User entity, List<String> expandFields) {
		return new UserResponse(entity);
	}

	@Override
	protected FilteringSpecificationsBuilder<User> getSpecificationBuilder() {
		return null;
	}
}

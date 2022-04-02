package com.example.covenant.journey.api.dto.user;

import com.example.covenant.journey.api.dto.general.AbstractCRUDController;
import com.example.covenant.journey.api.filters.models.FilteringSpecificationsBuilder;
import com.example.covenant.journey.api.shared.APIConstants;
import com.example.covenant.journey.model.user.User;
import com.example.covenant.journey.services.user.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = UserController.URL)
public class UserController extends AbstractCRUDController<
        UserResponse,
		User,
        UserRequest,
        UserService> {

    public static final String URL = APIConstants.USER_API;

	@Override
	public UserResponse convertEntityToResponse(User entity, List<String> expandFields) {
		return new UserResponse(entity);
	}

	@Override
	protected FilteringSpecificationsBuilder<User> getSpecificationBuilder() {
		return null;
	}
}

package com.example.covenant.journey.api.dto.user;

import com.example.covenant.journey.model.user.User;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotBlank;

public class CurrentUserUpdateRequest {

	@NotBlank
	@ApiModelProperty(notes = "", example = "Jonathan Josuke")
	private String fullName;

	@ApiModelProperty(notes = "", example = "I'm Jonathan Josuke")
	private String description;

	public User updateEntity(User entity) {
		entity.setFullName(this.fullName);
		entity.setDescription(this.description);
		return entity;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}

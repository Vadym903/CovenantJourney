package com.example.covenant.journey.api.dto.user;

import com.example.covenant.journey.api.dto.general.AbstractRequest;
import com.example.covenant.journey.model.user.User;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest extends AbstractRequest<User> {

    @NotBlank
    @ApiModelProperty(notes = "", example = "Jonathan Josuke")
    private String fullName;

    @Size(min = 3)
    @NotBlank
    @ApiModelProperty(notes = "", example = "jonathan_josuke")
    private String login;

    @NotBlank
    @Size(min = 3)
    @ApiModelProperty(notes = "", example = "pass")
    private String password;

    @Email
    @NotBlank
    @ApiModelProperty(notes = "", example = "joger@gmail.com")
    private String email;

    private Long photoId;

    @ApiModelProperty(notes = "", example = "I'm Jonathan Josuke")
    private String description;

    @Override
    public User createEntity() {
        return updateEntity(new User());
    }

    @Override
    public User updateEntity(User entity) {
        entity.setFullName(this.fullName);
        entity.setLogin(this.login);
        entity.setPassword(this.password);
        entity.setEmail(this.email);
        entity.setDescription(this.description);
        return entity;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public void setPhotoId(Long photoId) {
        this.photoId = photoId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

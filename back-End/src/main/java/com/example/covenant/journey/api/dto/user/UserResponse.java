package com.example.covenant.journey.api.dto.user;

import com.example.covenant.journey.api.dto.general.AbstractResponse;
import com.example.covenant.journey.model.user.User;
import com.example.covenant.journey.model.user.UserRole;

public class UserResponse extends AbstractResponse {

    private String fullName;

    private String login;

    private String email;

    private UserRole role;

    private Long photoId;

    private String description;

    public UserResponse() {

    }

    public UserResponse(User user) {
        super(user);
        this.fullName = user.getFullName();
        this.login = user.getLogin();
        this.email = user.getEmail();
        this.role = user.getRole();
        this.description = user.getDescription();
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
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

package com.example.covenant.journey.api.models.user;

import com.example.covenant.journey.api.models.general.PrimaryRequest;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserRequest implements PrimaryRequest {

    @NotBlank
    @Size(min = 4)
    private String firstName;

    @NotBlank
    @Size(min = 4)
    private String secondName;

    @Size(min = 4)
    @NotBlank
    private String login;

    @NotBlank
    @Size(min = 6)
    private String password;

    @Email
    @NotBlank
    private String email;

    private Long photoId;

    private String description;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
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

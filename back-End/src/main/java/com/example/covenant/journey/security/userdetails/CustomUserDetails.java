package com.example.covenant.journey.security.userdetails;

import com.example.covenant.journey.models.user.UserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private String login;
    private String password;
    private Collection<? extends GrantedAuthority> grantedAuthorities;
    private final transient UserEntity userEntity;

    public CustomUserDetails(String login, String password, Collection<? extends GrantedAuthority> grantedAuthorities, UserEntity userEntity) {
        this.login = login;
        this.password = password;
        this.grantedAuthorities = grantedAuthorities;
        this.userEntity = userEntity;
    }

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public static CustomUserDetails fromUserEntityToCustomUserDetails(UserEntity userEntity) {
        CustomUserDetails c = new CustomUserDetails(userEntity);
        c.login = userEntity.getLogin();
        c.password = userEntity.getPassword();
        c.grantedAuthorities = Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().name()));
        return c;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}

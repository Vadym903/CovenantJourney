package com.example.covenant.journey.security.userdetails;

import com.example.covenant.journey.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return CustomUserDetails.fromUserEntityToCustomUserDetails(userService.findByLogin(login));
    }
}

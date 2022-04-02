package com.example.covenant.journey.services.user;

import com.example.covenant.journey.model.user.User;
import com.example.covenant.journey.model.user.UserRole;
import com.example.covenant.journey.security.userdetails.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    public boolean isAdministrator() {
        return isAdministrator(getCurrentUser());
    }

    public boolean isAdministrator(User user) {
        return user.getRole().equals(UserRole.ROLE_ADMIN);
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object user = authentication.getPrincipal();
            if (user instanceof CustomUserDetails) {
                return ((CustomUserDetails) user).getUserEntity();
            }
        }
        return null;
    }
}

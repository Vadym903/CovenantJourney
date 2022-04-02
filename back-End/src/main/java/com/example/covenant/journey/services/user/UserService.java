package com.example.covenant.journey.services.user;

import com.example.covenant.journey.models.user.User;
import com.example.covenant.journey.models.user.UserRole;
import com.example.covenant.journey.repositories.BaseRepository;
import com.example.covenant.journey.repositories.user.UserRepository;
import com.example.covenant.journey.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<User> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected BaseRepository<User> getRepository() {
        return userRepository;
    }

    @Override
    public User create(User entity) {
        entity.setRole(UserRole.ROLE_USER);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return super.create(entity);
    }

    public User findUserByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    public User findByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}

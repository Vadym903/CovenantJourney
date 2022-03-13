package com.example.covenant.journey.services.user;

import com.example.covenant.journey.models.user.UserEntity;
import com.example.covenant.journey.models.user.UserRole;
import com.example.covenant.journey.repositories.BaseRepository;
import com.example.covenant.journey.repositories.user.UserRepository;
import com.example.covenant.journey.services.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService extends AbstractService<UserEntity> {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    protected BaseRepository<UserEntity> getRepository() {
        return userRepository;
    }

    @Override
    public UserEntity create(UserEntity entity) {
        entity.setRole(UserRole.ROLE_USER);
        entity.setPassword(passwordEncoder.encode(entity.getPassword()));

        return super.create(entity);
    }

    public UserEntity findUserByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    public UserEntity findByLogin(String login) {
        return userRepository.findFirstByLogin(login);
    }

    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}

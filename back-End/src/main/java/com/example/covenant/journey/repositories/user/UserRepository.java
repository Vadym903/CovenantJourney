package com.example.covenant.journey.repositories.user;

import com.example.covenant.journey.models.user.UserEntity;
import com.example.covenant.journey.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<UserEntity> {

    UserEntity findFirstByLogin(String login);
}

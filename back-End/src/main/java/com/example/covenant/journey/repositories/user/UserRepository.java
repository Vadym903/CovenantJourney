package com.example.covenant.journey.repositories.user;

import com.example.covenant.journey.models.user.User;
import com.example.covenant.journey.repositories.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User> {

    User findFirstByLogin(String login);
}

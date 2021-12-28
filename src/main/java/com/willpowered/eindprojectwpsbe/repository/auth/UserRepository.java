package com.willpowered.eindprojectwpsbe.repository.auth;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}

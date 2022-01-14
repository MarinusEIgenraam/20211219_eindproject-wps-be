package com.willpowered.eindprojectwpsbe.auth;


import com.willpowered.eindprojectwpsbe.auth.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);
}

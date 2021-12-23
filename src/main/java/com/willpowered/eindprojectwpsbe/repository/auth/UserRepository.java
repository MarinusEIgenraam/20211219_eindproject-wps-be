package com.willpowered.eindprojectwpsbe.repository.auth;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
}

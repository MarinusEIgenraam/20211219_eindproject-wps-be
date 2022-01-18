package com.willpowered.eindprojectwpsbe.auth;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUsername(String username);

    @Query("select users from User users where ':authority' member of users.authorities")
    List<User> getAllByAuthority(String authority, Pageable pageable);}

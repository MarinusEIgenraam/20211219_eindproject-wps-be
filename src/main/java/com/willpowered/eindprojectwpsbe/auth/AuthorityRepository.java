package com.willpowered.eindprojectwpsbe.auth;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findAllByAuthority(String authority, Pageable pageable);
}

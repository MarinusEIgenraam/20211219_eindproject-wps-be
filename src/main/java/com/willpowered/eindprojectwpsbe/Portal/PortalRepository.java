package com.willpowered.eindprojectwpsbe.Portal;

import com.willpowered.eindprojectwpsbe.auth.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {
    Optional<Portal> findByUser (User user);

}

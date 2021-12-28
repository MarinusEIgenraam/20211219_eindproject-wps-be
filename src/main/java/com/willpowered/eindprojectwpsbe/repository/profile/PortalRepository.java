package com.willpowered.eindprojectwpsbe.repository.profile;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {
    Optional<Portal> findByUser (User user);

}

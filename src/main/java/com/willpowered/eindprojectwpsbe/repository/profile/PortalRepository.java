package com.willpowered.eindprojectwpsbe.repository.profile;

import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalRepository extends JpaRepository<Portal, Long> {
}

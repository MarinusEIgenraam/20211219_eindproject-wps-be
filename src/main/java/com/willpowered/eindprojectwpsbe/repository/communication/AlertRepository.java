package com.willpowered.eindprojectwpsbe.repository.communication;

import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByPortal(Portal portal);

    Set<Alert> findByPortal(Portal portal);
}
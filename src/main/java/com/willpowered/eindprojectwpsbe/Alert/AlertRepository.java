package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface AlertRepository extends JpaRepository<Alert, Long> {
    List<Alert> findAllByPortal(Portal portal, Pageable pageable);

    Set<Alert> findByPortal(Portal portal);
}
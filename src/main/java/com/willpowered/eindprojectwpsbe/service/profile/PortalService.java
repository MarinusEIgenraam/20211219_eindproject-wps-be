package com.willpowered.eindprojectwpsbe.service.profile;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import com.willpowered.eindprojectwpsbe.repository.profile.PortalRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class PortalService {

    @Autowired
    private PortalRepository portalRepository;

    public List<Portal> getPortals() {
        return portalRepository.findAll();
    }

    public Portal getPortal(Long id) {
        Optional<Portal> portal = portalRepository.findById(id);

        if(portal.isPresent()) {
            return portal.get();
        } else {
            throw new RecordNotFoundException("Portal does not exist");
        }
    }

    public Optional<Portal> getUserPortal(User user) {
        return portalRepository.findByUser(user);
    }

    public Portal savePortal(Portal portal) {
        return portalRepository.save(portal);
    }

    public void updatePortal(Long id, Portal portal) {
        Optional<Portal> optionalPortal = portalRepository.findById(id);
        if (optionalPortal.isPresent()) {
            portalRepository.deleteById(id);
            portalRepository.save(portal);
        } else {
            throw new RecordNotFoundException("Portal does not exist");
        }
    }

    public void deletePortal(Long id) {
        portalRepository.deleteById(id);
    }


}



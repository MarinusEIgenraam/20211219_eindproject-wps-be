package com.willpowered.eindprojectwpsbe.Portal;

import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserService;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
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

    @Autowired
    UserService userService;

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

    public Portal getUserPortal(User user) {
        Optional<Portal> optionalPortal = portalRepository.findByUser(user);
        if (optionalPortal.isPresent()) {
            return optionalPortal.get();
        } else {
            throw new RecordNotFoundException("Portal does not exist");
        }
    }

    public Portal savePortal(PortalInputDto dto) {
        User user = userService.getUser(dto.username);
        Portal portal = dto.toPortal();
        portal.setUser(user);
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



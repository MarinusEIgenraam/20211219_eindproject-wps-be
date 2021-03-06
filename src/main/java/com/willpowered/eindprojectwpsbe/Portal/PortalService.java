package com.willpowered.eindprojectwpsbe.Portal;

import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class PortalService {

    @Autowired
    UserService userService;
    @Autowired
    private PortalRepository portalRepository;

    //////////////////////////////
    //// Create

    public Portal savePortal(PortalInputDto dto) {
        User user = userService.getUser(dto.username);
        Portal portal = dto.toPortal();
        portal.setPortalOwner(user);
        return portalRepository.save(portal);
    }

    //////////////////////////////
    //// Read

    public List<Portal> getPortals() {
        return portalRepository.findAll();
    }

    public Portal getPortal(Long id) {
        var portal = portalRepository.findById(id);

        if (portal.isPresent()) {
            return portal.get();
        } else {
            throw new RecordNotFoundException("Portal does not exist");
        }
    }

    public Portal getUserPortal(User user) {
        var optionalPortal = portalRepository.findByPortalOwner(user);
        if (optionalPortal.isPresent()) {
            return optionalPortal.get();
        } else {
            throw new RecordNotFoundException("Portal does not exist");
        }
    }

    //////////////////////////////
    //// Update

    public void updatePortal(Long id, Portal portal) {
        var optionalPortal = portalRepository.findById(id);
        if (optionalPortal.isPresent()) {
            Portal newPortal = portal;
            newPortal.setId(id);
            portalRepository.save(newPortal);
        } else {
            throw new RecordNotFoundException("Portal does not exist");
        }
    }

    //////////////////////////////
    //// Delete

    public void deletePortal(Long id) {
        portalRepository.deleteById(id);
    }


}



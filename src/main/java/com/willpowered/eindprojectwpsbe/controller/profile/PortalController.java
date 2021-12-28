package com.willpowered.eindprojectwpsbe.controller.profile;

import com.willpowered.eindprojectwpsbe.dto.profile.Portal.PortalDTO;
import com.willpowered.eindprojectwpsbe.dto.profile.Portal.PortalInputDTO;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import com.willpowered.eindprojectwpsbe.service.profile.PortalService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portals")
@AllArgsConstructor
@Slf4j
public class PortalController {

    @Autowired
    private PortalService portalService;

    @GetMapping("/{id}")
    public PortalDTO getPortal(@PathVariable("id") Long id) {
        var portal = portalService.getPortal(id);
        return PortalDTO.fromPortal(portal);
    }

    @PostMapping
    public PortalDTO savePortal(@RequestBody PortalInputDTO dto) {
        var portal = portalService.savePortal(dto.toPortal());
        return PortalDTO.fromPortal(portal);
    }

    @PutMapping("/{id}")
    public PortalDTO updatePortal(@PathVariable Long id, @RequestBody Portal portal) {
        portalService.updatePortal(id, portal);
        return PortalDTO.fromPortal(portal);
    }

    @DeleteMapping("/{id}")
    public void deletePortal(@PathVariable("id") Long id) {
        portalService.deletePortal(id);
    }
}
package com.willpowered.eindprojectwpsbe.controller.profile;

import com.willpowered.eindprojectwpsbe.dto.profile.Portal.PortalDto;
import com.willpowered.eindprojectwpsbe.dto.profile.Portal.PortalInputDto;
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
    public PortalDto getPortal(@PathVariable("id") Long id) {
        var portal = portalService.getPortal(id);
        return PortalDto.fromPortal(portal);
    }

    @PostMapping
    public PortalDto savePortal(@RequestBody PortalInputDto Dto) {
        var portal = portalService.savePortal(Dto.toPortal());
        return PortalDto.fromPortal(portal);
    }

    @PutMapping("/{id}")
    public PortalDto updatePortal(@PathVariable Long id, @RequestBody Portal portal) {
        portalService.updatePortal(id, portal);
        return PortalDto.fromPortal(portal);
    }

    @DeleteMapping("/{id}")
    public void deletePortal(@PathVariable("id") Long id) {
        portalService.deletePortal(id);
    }
}
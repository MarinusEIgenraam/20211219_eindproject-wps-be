package com.willpowered.eindprojectwpsbe.Portal;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/portals")
@AllArgsConstructor
@Slf4j
public class PortalController {

    @Autowired
    private PortalService portalService;

    //////////////////////////////
    //// Create

    @PostMapping
    public PortalDto savePortal(@RequestBody PortalInputDto dto) {
        var portal = portalService.savePortal(dto);
        return PortalDto.fromPortal(portal);
    }

    //////////////////////////////
    //// Read

    @GetMapping("/{id}")
    public PortalDto getPortal(@PathVariable("id") Long id) {
        var portal = portalService.getPortal(id);
        return PortalDto.fromPortal(portal);
    }

    //////////////////////////////
    //// Update

    @PutMapping("/{id}")
    public PortalDto updatePortal(@PathVariable Long id, @RequestBody Portal portal) {
        portalService.updatePortal(id, portal);
        return PortalDto.fromPortal(portal);
    }

    //////////////////////////////
    //// Delete

    @DeleteMapping("/{id}")
    public void deletePortal(@PathVariable("id") Long id) {
        portalService.deletePortal(id);
    }
}
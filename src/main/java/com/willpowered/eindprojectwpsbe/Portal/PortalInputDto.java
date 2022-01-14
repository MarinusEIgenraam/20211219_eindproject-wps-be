package com.willpowered.eindprojectwpsbe.Portal;

import lombok.var;

public class PortalInputDto {

    public Long id;
    public String username;
    public Long settingsSchemaId;

    public Portal toPortal() {
        var portal = new Portal();

        portal.setId(id);

        return portal;
    }
}
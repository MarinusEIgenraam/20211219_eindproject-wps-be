package com.willpowered.eindprojectwpsbe.dto.profile.Portal;

import com.willpowered.eindprojectwpsbe.model.profile.Portal;
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
package com.willpowered.eindprojectwpsbe.Portal;

public class PortalInputDto {

    public Long id;
    public String username;

    public Portal toPortal() {
        var portal = new Portal();

        portal.setId(id);

        return portal;
    }
}
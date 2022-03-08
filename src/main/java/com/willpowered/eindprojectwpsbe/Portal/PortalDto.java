package com.willpowered.eindprojectwpsbe.Portal;

import com.willpowered.eindprojectwpsbe.User.UserDto;

public class PortalDto {

    public Long id;
    public UserDto portalOwner;

    public static PortalDto fromPortal(Portal portal) {
        var dto = new PortalDto();

        dto.id = portal.getId();
        dto.portalOwner = UserDto.fromUser(portal.getPortalOwner());

        return dto;
    }


}
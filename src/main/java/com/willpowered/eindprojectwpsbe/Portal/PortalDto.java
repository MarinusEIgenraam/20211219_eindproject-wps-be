package com.willpowered.eindprojectwpsbe.Portal;

import com.willpowered.eindprojectwpsbe.User.UserDto;
import lombok.var;

public class PortalDto {

    public Long id;
    public UserDto user;

    public static PortalDto fromPortal(Portal portal) {
        var dto = new PortalDto();

        dto.id= portal.getId();
        dto.user= UserDto.fromUser(portal.getUser());

        return dto;
    }


}
package com.willpowered.eindprojectwpsbe.Portal;

import com.willpowered.eindprojectwpsbe.SettingSchema.SettingSchemaDto;
import com.willpowered.eindprojectwpsbe.auth.User.UserDto;
import lombok.var;

public class PortalDto {

    public Long id;
    public UserDto user;
    public SettingSchemaDto settingsSchema;

    public static PortalDto fromPortal(Portal portal) {
        var dto = new PortalDto();

        dto.id= portal.getId();
        dto.user= UserDto.fromUser(portal.getUser());
        dto.settingsSchema= SettingSchemaDto.fromSettingSchema(portal.getSettingsSchema());

        return dto;
    }


}
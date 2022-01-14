package com.willpowered.eindprojectwpsbe.dto.profile.Portal;

import com.willpowered.eindprojectwpsbe.dto.auth.User.UserDto;
import com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema.SettingSchemaDto;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
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
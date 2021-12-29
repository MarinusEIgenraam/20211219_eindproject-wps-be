package com.willpowered.eindprojectwpsbe.dto.profile.Portal;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import lombok.var;

public class PortalDto {

    public Long id;
    public User user;
    public SettingSchema settingsSchema;

    public static PortalDto fromPortal(Portal portal) {
        var Dto = new PortalDto();

        Dto.id= portal.getId();
        Dto.user= portal.getUser();
        Dto.settingsSchema= portal.getSettingsSchema();

        return Dto;
    }


}
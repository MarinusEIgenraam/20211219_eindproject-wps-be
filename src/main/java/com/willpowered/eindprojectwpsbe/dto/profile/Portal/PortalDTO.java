package com.willpowered.eindprojectwpsbe.dto.profile.Portal;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import lombok.var;

public class PortalDTO {

    public Long id;
    public User user;
    public SettingSchema settingsSchema;

    public static PortalDTO fromPortal(Portal portal) {
        var dto = new PortalDTO();

        dto.id= portal.getId();
        dto.user= portal.getUser();
        dto.settingsSchema= portal.getSettingsSchema();

        return dto;
    }


}
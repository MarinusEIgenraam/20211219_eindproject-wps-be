package com.willpowered.eindprojectwpsbe.dto.profile.Portal;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.profile.Portal;
import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import lombok.var;

public class PortalInputDTO {

    public Long id;
    public User user;
    public SettingSchema settingsSchema;

    public Portal toPortal() {
        var portal = new Portal();

        portal.setId(id);
        portal.setUser(user);
        portal.setSettingsSchema(settingsSchema);

        return portal;
    }
}
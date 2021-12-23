package com.willpowered.eindprojectwpsbe.dto.profile.portal;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortalResponse {

    private Long id;
    private String name;
    private User user;
    private SettingSchema settingSchema;
}

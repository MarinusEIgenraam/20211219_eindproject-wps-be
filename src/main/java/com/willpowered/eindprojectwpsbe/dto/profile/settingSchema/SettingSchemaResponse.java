package com.willpowered.eindprojectwpsbe.dto.profile.settingSchema;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SettingSchemaResponse {

    private Long id;
    private boolean commentAlert;
    private boolean taskAlert;

}
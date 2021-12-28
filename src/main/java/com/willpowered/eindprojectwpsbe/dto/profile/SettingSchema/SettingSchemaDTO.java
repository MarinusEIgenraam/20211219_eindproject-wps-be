package com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema;

import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import lombok.var;

public class SettingSchemaDTO {

    public Long id;
    public boolean commentAlert;
    public boolean taskAlert;

    public static SettingSchemaDTO fromSettingSchema(SettingSchema settingsSchema) {
        var dto = new SettingSchemaDTO();

        dto.id = settingsSchema.getId();
        dto.commentAlert = settingsSchema.getCommentAlert();
        dto.taskAlert = settingsSchema.getTaskAlert();

        return dto;
    }


}
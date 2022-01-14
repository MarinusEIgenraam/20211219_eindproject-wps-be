package com.willpowered.eindprojectwpsbe.SettingSchema;

import lombok.var;

public class SettingSchemaDto {

    public Long id;
    public Boolean commentAlert;
    public Boolean taskAlert;

    public static SettingSchemaDto fromSettingSchema(SettingSchema settingsSchema) {
        var Dto = new SettingSchemaDto();

        Dto.id = settingsSchema.getId();
        Dto.commentAlert = settingsSchema.getCommentAlert();
        Dto.taskAlert = settingsSchema.getTaskAlert();

        return Dto;
    }


}
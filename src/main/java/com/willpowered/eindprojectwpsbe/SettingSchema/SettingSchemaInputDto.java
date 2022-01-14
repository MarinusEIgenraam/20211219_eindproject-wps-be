package com.willpowered.eindprojectwpsbe.SettingSchema;

import lombok.var;

public class SettingSchemaInputDto {

    public Long id;
    public Boolean commentAlert;
    public Boolean taskAlert;

    public SettingSchema toSettingSchema() {
        var settingSchema = new SettingSchema();

        settingSchema.setId(id);
        settingSchema.setCommentAlert(commentAlert);
        settingSchema.setTaskAlert(taskAlert);

        return settingSchema;
    }
}
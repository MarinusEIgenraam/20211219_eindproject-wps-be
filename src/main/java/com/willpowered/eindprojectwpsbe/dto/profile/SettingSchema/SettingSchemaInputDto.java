package com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema;

import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
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
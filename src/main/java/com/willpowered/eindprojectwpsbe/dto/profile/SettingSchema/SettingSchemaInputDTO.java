package com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema;

import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import lombok.var;

public class SettingSchemaInputDTO {

    public Long id;
    public boolean commentAlert;
    public boolean taskAlert;

    public SettingSchema toSettingSchema() {
        var settingSchema = new SettingSchema();

        settingSchema.setId(id);
        settingSchema.setCommentAlert(commentAlert);
        settingSchema.setTaskAlert(taskAlert);

        return settingSchema;
    }
}
package com.willpowered.eindprojectwpsbe.controller.profile;

import com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema.SettingSchemaDto;
import com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema.SettingSchemaInputDto;
import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import com.willpowered.eindprojectwpsbe.service.profile.SettingSchemaService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/settingSchemas")
@AllArgsConstructor
@Slf4j
public class SettingSchemaController {

    @Autowired
    private SettingSchemaService settingSchemaService;

    @GetMapping("/{id}")
    public SettingSchemaDto getSettingSchema(@PathVariable("id") Long id) {
        var settingSchema = settingSchemaService.getSettingSchema(id);
        return SettingSchemaDto.fromSettingSchema(settingSchema);
    }

    @PostMapping
    public SettingSchemaDto saveSettingSchema(@RequestBody SettingSchemaInputDto Dto) {
        var settingSchema = settingSchemaService.saveSettingSchema(Dto.toSettingSchema());
        return SettingSchemaDto.fromSettingSchema(settingSchema);
    }

    @PutMapping("/{id}")
    public SettingSchemaDto updateSettingSchema(@PathVariable Long id, @RequestBody SettingSchema settingSchema) {
        settingSchemaService.updateSettingSchema(id, settingSchema);
        return SettingSchemaDto.fromSettingSchema(settingSchema);
    }

    @DeleteMapping("/{id}")
    public void deleteSettingSchema(@PathVariable("id") Long id) {
        settingSchemaService.deleteSettingSchema(id);
    }
}
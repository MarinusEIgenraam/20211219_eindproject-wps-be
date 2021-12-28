package com.willpowered.eindprojectwpsbe.controller.profile;

import com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema.SettingSchemaDTO;
import com.willpowered.eindprojectwpsbe.dto.profile.SettingSchema.SettingSchemaInputDTO;
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
    public SettingSchemaDTO getSettingSchema(@PathVariable("id") Long id) {
        var settingSchema = settingSchemaService.getSettingSchema(id);
        return SettingSchemaDTO.fromSettingSchema(settingSchema);
    }

    @PostMapping
    public SettingSchemaDTO saveSettingSchema(@RequestBody SettingSchemaInputDTO dto) {
        var settingSchema = settingSchemaService.saveSettingSchema(dto.toSettingSchema());
        return SettingSchemaDTO.fromSettingSchema(settingSchema);
    }

    @PutMapping("/{id}")
    public SettingSchemaDTO updateSettingSchema(@PathVariable Long id, @RequestBody SettingSchema settingSchema) {
        settingSchemaService.updateSettingSchema(id, settingSchema);
        return SettingSchemaDTO.fromSettingSchema(settingSchema);
    }

    @DeleteMapping("/{id}")
    public void deleteSettingSchema(@PathVariable("id") Long id) {
        settingSchemaService.deleteSettingSchema(id);
    }
}
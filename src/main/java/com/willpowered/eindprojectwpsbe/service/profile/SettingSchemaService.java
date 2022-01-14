package com.willpowered.eindprojectwpsbe.service.profile;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import com.willpowered.eindprojectwpsbe.repository.profile.SettingSchemaRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class SettingSchemaService {

    @Autowired
    private SettingSchemaRepository settingSchemaRepository;

    public SettingSchema getSettingSchema(Long id) {
        Optional<SettingSchema> settingSchema = settingSchemaRepository.findById(id);

        if(settingSchema.isPresent()) {
            return settingSchema.get();
        } else {
            throw new RecordNotFoundException("Setting schema does not exist");
        }
    }

    public SettingSchema saveSettingSchema(SettingSchema settingSchema) {
        return settingSchemaRepository.save(settingSchema);
    }

    public void updateSettingSchema(Long id, SettingSchema settingSchema) {
        Optional<SettingSchema> optionalSettingSchema = settingSchemaRepository.findById(id);
        if (optionalSettingSchema.isPresent()) {
            settingSchemaRepository.deleteById(id);
            settingSchemaRepository.save(settingSchema);
        } else {
            throw new RecordNotFoundException("Setting schema does not exist");
        }
    }

    public void deleteSettingSchema(Long id) {
        settingSchemaRepository.deleteById(id);
    }


}



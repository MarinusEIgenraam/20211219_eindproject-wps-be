package com.willpowered.eindprojectwpsbe.repository.profile;

import com.willpowered.eindprojectwpsbe.model.profile.SettingSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingSchemaRepository extends JpaRepository<SettingSchema, Long> {
}

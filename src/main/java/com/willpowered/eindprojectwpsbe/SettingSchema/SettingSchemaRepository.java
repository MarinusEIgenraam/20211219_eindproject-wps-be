package com.willpowered.eindprojectwpsbe.SettingSchema;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingSchemaRepository extends JpaRepository<SettingSchema, Long> {

}

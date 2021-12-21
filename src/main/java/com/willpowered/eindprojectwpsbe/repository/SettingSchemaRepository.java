package com.willpowered.eindprojectwpsbe.repository;

import com.willpowered.eindprojectwpsbe.model.Category;
import com.willpowered.eindprojectwpsbe.model.SettingSchema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SettingSchemaRepository extends JpaRepository<SettingSchema, Long> {
}

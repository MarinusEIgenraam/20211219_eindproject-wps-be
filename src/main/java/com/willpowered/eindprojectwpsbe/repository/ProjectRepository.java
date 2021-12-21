package com.willpowered.eindprojectwpsbe.repository;

import com.willpowered.eindprojectwpsbe.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

}

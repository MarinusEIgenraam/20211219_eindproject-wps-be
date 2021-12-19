package com.willpowered.eindprojectwpsbe.repository;

import com.willpowered.eindprojectwpsbe.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}

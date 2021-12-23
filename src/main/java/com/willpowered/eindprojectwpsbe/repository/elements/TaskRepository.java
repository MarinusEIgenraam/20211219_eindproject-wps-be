package com.willpowered.eindprojectwpsbe.repository.elements;

import com.willpowered.eindprojectwpsbe.model.elements.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}

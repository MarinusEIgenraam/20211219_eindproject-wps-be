package com.willpowered.eindprojectwpsbe.repository.elements;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByParentProject(Project project);

    List<Task> findAllByTaskOwner(User user);

    Optional<Task> findByTaskName(String taskName);

    List<Task> findByTaskNameContainingIgnoreCase(String taskName);

    List<Task> findAllByParentTask(Task task);
}

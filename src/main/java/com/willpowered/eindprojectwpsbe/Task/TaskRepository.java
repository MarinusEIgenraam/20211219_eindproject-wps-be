package com.willpowered.eindprojectwpsbe.Task;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.Models.User;
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

package com.willpowered.eindprojectwpsbe.repository.elements;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {


    @Query(
            "SELECT t FROM Task t WHERE :project = t.parentProject AND (t.taskOwner = :user OR t.parentProject.projectOwner = :user OR t.parentProject.publiclyVisible = true OR :user MEMBER OF t.parentProject.collaborators)")
    List<Task> findAllByParentProject(@Param("project")Project project,
                                      @Param("user")User user);

    @Query(
            "SELECT t FROM Task t WHERE :taskOwner = t.taskOwner AND (t.taskOwner = :user OR t.parentProject.projectOwner = :user OR t.parentProject.publiclyVisible = true OR :user MEMBER OF t.parentProject.collaborators)")
    List<Task> findAllByTaskOwner(@Param("taskOwner")User taskOwner,
                                      @Param("user")User user);

    Optional<Task> findByTaskName(String taskName, User user);

    @Query(
            "SELECT t FROM Task t WHERE :task = t.parentTask AND (t.taskOwner = :user OR t.parentProject.projectOwner = :user OR t.parentProject.publiclyVisible = true OR :user MEMBER OF t.parentProject.collaborators)")
    List<Task> findAllByParentTask(@Param("task")Task task,
                                      @Param("user")User user);

}

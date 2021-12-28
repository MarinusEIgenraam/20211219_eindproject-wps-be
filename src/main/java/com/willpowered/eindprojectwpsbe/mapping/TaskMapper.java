package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskResponse;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Optional;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    @Mapping(target = "startTime", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "taskRequest.description")
    @Mapping(target = "parentProject", source = "project")
    @Mapping(target = "taskId", source = "taskRequest.taskId")
    @Mapping(target = "parentTask", source = "task")
    @Mapping(target = "taskName", source = "taskRequest.taskName")
    @Mapping(target = "taskOwner", source = "user")
    @Mapping(target = "endTime", source = "taskRequest.endTime")
    public abstract Task map(TaskRequest taskRequest, Task task, Project project, User user);

    @Mapping(target = "id", source = "taskId")
    @Mapping(target = "parentProjectName", source = "parentProject.projectName")
    @Mapping(target = "parentTaskName", source = "parentTask.taskName")
    @Mapping(target = "taskOwnerName", source = "taskOwner.username")
    @Mapping(target = "taskTaskList", source = "task.taskTaskList")
    public abstract TaskResponse mapToDto(Task task);

    public abstract Object map(TaskRequest taskRequest, Task task, User currentUser);
}

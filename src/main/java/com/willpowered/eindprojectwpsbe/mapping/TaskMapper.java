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

    @Mapping(target = "startTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "description", source = "taskRequest.description")
    @Mapping(target = "parentProject", source = "project")
    @Mapping(target = "parentTask", source = "task")
    public abstract Task map(TaskRequest taskRequest, Project project, User user);

    @Mapping(target = "parentProjectName", source = "parentProject.projectName")
    @Mapping(target = "parentTaskName", source = "parentTask.taskName")
    @Mapping(target = "userName", source = "taskOwner.username")
    public abstract TaskResponse mapToDto(Task task);

}

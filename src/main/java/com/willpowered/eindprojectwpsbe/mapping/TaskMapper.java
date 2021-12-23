package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskResponse;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    @Mapping(target = "startTime", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "description", source = "taskRequest.description")
    @Mapping(target = "parentProject", source = "project")
    @Mapping(target = "parentTask", source = "task")
    public abstract Task map(TaskRequest taskRequest, Project project, Task task, User user);

    @Mapping(target = "id", source = "taskId")
    @Mapping(target = "parentProjectName", source = "project.name")
    @Mapping(target = "taskProjectName", source = "task.name")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "isRunning", source = "isRunning")
    public abstract TaskResponse mapToDto(Task task);


}

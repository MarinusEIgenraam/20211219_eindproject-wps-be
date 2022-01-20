package com.willpowered.eindprojectwpsbe.Task;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.auth.UserDto;
import lombok.var;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDto {
    public Long taskId;
    public String taskName;
    public String description;
    public LocalDate startTime;
    @Nullable
    public LocalDate editedTime;
    public LocalDate endTime;
    public Boolean isRunning;
    public UserDto taskOwner;
    public List<TaskDto> taskTaskList;

    public static TaskDto fromTask(Task task) {

        ModelMapper modelMapper = new ModelMapper();

        var dto = new TaskDto();
        dto.taskId = task.getTaskId();
        dto.taskName = task.getTaskName();
        dto.description = task.getDescription();
        dto.startTime = task.getStartTime();
        dto.editedTime = task.getEditedTime();
        dto.endTime = task.getEndTime();
        dto.isRunning = task.getIsRunning();
        dto.taskOwner = UserDto.fromUser(task.getTaskOwner());
        dto.taskTaskList = task.getTaskTaskList().stream().map(p -> TaskDto.fromTask(p)).collect(Collectors.toList());
        return dto;
    }
}
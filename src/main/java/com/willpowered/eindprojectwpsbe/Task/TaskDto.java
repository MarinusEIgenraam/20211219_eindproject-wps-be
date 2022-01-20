package com.willpowered.eindprojectwpsbe.Task;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.auth.UserDto;
import com.willpowered.eindprojectwpsbe.config.ObjectMapperUtils;
import lombok.var;

import java.time.LocalDate;
import java.util.List;

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
    @Nullable
    @JsonSerialize
    public TaskDto parentTask;
    @Nullable
    @JsonSerialize
    public ProjectDto parentProject;
    public List<TaskDto> taskTaskList;

    public static TaskDto fromTask(Task task) {
        var dto = new TaskDto();
        dto.taskId = task.getTaskId();
        dto.taskName = task.getTaskName();
        dto.description = task.getDescription();
        dto.startTime = task.getStartTime();
        dto.editedTime = task.getEditedTime();
        dto.endTime = task.getEndTime();
        dto.isRunning = task.getIsRunning();
        dto.taskOwner = UserDto.fromUser(task.getTaskOwner());
        if (task.getParentProject() != null && task.getParentTask() == null) {
            dto.parentProject = ProjectDto.fromProject(task.getParentProject());
        } else {
            dto.parentTask = TaskDto.fromTask(task.getParentTask());
        }
        dto.taskTaskList = ObjectMapperUtils.mapAll(task.getTaskTaskList(), TaskDto.class);
        return dto;
    }
}
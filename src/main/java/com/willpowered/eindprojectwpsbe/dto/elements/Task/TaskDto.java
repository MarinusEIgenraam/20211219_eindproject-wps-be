package com.willpowered.eindprojectwpsbe.dto.elements.Task;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.dto.auth.User.UserDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;

import java.time.Instant;
import java.util.List;

public class TaskDto {
    private Long taskId;
    private String taskName;
    private String description;
    public Instant startTime;
    public Instant endTime;
    public Boolean isRunning;
    public UserDto taskOwner;
    @Nullable
    @JsonSerialize
    public TaskDto parentTask;
    @Nullable
    @JsonSerialize
    public ProjectDto parentProject;
    public List<Task> taskTaskList;

    public static TaskDto fromTask(Task task) {
        var dto = new TaskDto();
        dto.taskId = task.getTaskId();
        dto.taskName = task.getTaskName();
        dto.description = task.getDescription();
        dto.startTime = task.getStartTime();
        dto.endTime = task.getEndTime();
        dto.isRunning = task.getIsRunning();
        dto.taskOwner = UserDto.fromUser(task.getTaskOwner());
        if (task.getParentProject() != null && task.getParentTask() == null) {
            dto.parentProject = ProjectDto.fromProject(task.getParentProject());
        } else {
            dto.parentTask = TaskDto.fromTask(task.getParentTask());
        }
        dto.taskTaskList = task.getTaskTaskList();
        return dto;
    }
}
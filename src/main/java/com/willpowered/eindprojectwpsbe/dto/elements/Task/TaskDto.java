package com.willpowered.eindprojectwpsbe.dto.elements.Task;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.dto.auth.User.UserDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;

import java.time.Instant;

public class TaskDto {
    private Long taskId;
    private String taskName;
    private String description;
    public Instant startTime;
    public Instant endTime;
    public Boolean isRunning;
    public UserDto taskOwner;
    public Task parentTask;
    public ProjectDto parentProject;

    public static TaskDto fromTask(Task task) {
        var Dto = new TaskDto();
        Dto.taskId = task.getTaskId();
        Dto.taskName = task.getTaskName();
        Dto.description = task.getDescription();
        Dto.startTime = task.getStartTime();
        Dto.endTime = task.getEndTime();
        Dto.isRunning = task.getIsRunning();
        Dto.taskOwner = UserDto.fromUser(task.getTaskOwner());
        Dto.parentTask = task.getParentTask();
        Dto.parentProject = ProjectDto.fromProject(task.getParentProject());
        return Dto;
    }
}
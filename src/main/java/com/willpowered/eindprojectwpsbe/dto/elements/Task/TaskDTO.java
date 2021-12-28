package com.willpowered.eindprojectwpsbe.dto.elements.Task;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;

import java.time.Instant;

public class TaskDTO {
    private Long taskId;
    private String taskName;
    private String description;
    public Instant startTime;
    public Instant endTime;
    public boolean isRunning;
    public User taskOwner;
    public Task parentTask;
    public Project parentProject;

    public static TaskDTO fromTask(Task task) {
        var dto = new TaskDTO();
        dto.taskId = task.getTaskId();
        dto.taskName = task.getTaskName();
        dto.description = task.getDescription();
        dto.startTime = task.getStartTime();
        dto.endTime = task.getEndTime();
        dto.isRunning = task.getIsRunning();
        dto.taskOwner = task.getTaskOwner();
        dto.parentTask = task.getParentTask();
        dto.parentProject = task.getParentProject();
        return dto;
    }
}
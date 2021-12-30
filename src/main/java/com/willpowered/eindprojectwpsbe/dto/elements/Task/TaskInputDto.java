package com.willpowered.eindprojectwpsbe.dto.elements.Task;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;

import java.time.Instant;

public class TaskInputDto {
    public Long taskId;
    public String taskName;
    public String description;
    public Instant startTime;
    public Instant endTime;
    public Boolean isRunning;
    @Nullable
    public User taskOwner;
    @Nullable
    public Task parentTask;
    @Nullable
    public Project parentProject;

    public Task toTask() {
        var task = new Task();

        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        task.setIsRunning(isRunning);
        task.setTaskOwner(taskOwner);
        task.setParentProject(parentProject);
        task.setParentTask(parentTask);

        return task;
    }
}
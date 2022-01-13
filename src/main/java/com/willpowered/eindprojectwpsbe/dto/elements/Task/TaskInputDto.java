package com.willpowered.eindprojectwpsbe.dto.elements.Task;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;
import org.springframework.lang.Nullable;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;

public class TaskInputDto {
    public Long taskId;
    public String taskName;
    public String description;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public Boolean isRunning;
    public String taskOwnerName;
    @Nullable
    public Long parentTaskId;
    @Nullable
    public Long parentProjectId;

    public Task toTask() {
        var task = new Task();

        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setStartTime(startTime);
        task.setEndTime(endTime);
        task.setIsRunning(isRunning);

        return task;
    }
}
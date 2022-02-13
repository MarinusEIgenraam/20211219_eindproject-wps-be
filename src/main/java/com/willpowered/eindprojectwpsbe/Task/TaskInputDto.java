package com.willpowered.eindprojectwpsbe.Task;

import lombok.Builder;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Builder
public class TaskInputDto {
    public Long taskId;
    public String taskName;
    public String description;
    public LocalDate startTime;
    @Nullable
    public LocalDate editedTime;
    public LocalDate endTime;
    public String taskOwner;
    @Nullable
    public Long parentTaskId;
    @Nullable
    public Long parentProjectId;
    @Nullable
    public List<TaskInputDto> taskTaskList;

    public Task toTask() {
        var task = new Task();

        task.setTaskId(taskId);
        task.setTaskName(taskName);
        task.setDescription(description);
        task.setStartTime(startTime);
        task.setEditedTime(editedTime);
        task.setEndTime(endTime);
        task.setTaskTaskList(taskTaskList.stream().map(p-> p.toTask()).collect(Collectors.toList()));

        return task;
    }
}
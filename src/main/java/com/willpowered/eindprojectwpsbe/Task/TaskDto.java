package com.willpowered.eindprojectwpsbe.Task;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.Project.ParentProjectDto;
import com.willpowered.eindprojectwpsbe.User.UserDto;
import lombok.var;

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
    @Nullable
    public ParentProjectDto parentProject;
    @Nullable
    public ParentTaskDto parentTask;
    @Nullable
    public List<TaskDto> taskTaskList;

    public static TaskDto fromTask(Task task) {

        var dto = new TaskDto();
        dto.taskId = task.getTaskId();
        dto.taskName = task.getTaskName();
        dto.description = task.getDescription();
        dto.startTime = task.getStartTime();
        dto.editedTime = task.getEditedTime();
        dto.endTime = task.getEndTime();
        if (task.getParentProject() != null && task.getParentTask() == null) {
            dto.parentProject = ParentProjectDto.fromParentProject(task.getParentProject());
        } else {
            dto.parentTask = ParentTaskDto.fromParentTask(task.getParentTask());
        }
        dto.isRunning = task.getIsRunning();
        dto.taskOwner = UserDto.fromUser(task.getTaskOwner());
        if (task.getTaskTaskList() != null) {
            dto.taskTaskList = task.getTaskTaskList().stream().map(p -> TaskDto.fromTask(p)).collect(Collectors.toList());
        }

        return dto;
    }
}
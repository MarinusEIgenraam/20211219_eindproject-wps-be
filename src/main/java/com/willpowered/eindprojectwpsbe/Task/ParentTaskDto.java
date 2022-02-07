package com.willpowered.eindprojectwpsbe.Task;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.Project.ParentProjectDto;
import com.willpowered.eindprojectwpsbe.User.UserDto;
import lombok.var;

import java.time.LocalDate;
import java.util.List;

public class ParentTaskDto {
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
    public List<ParentTaskDto> taskTaskList;

    public static ParentTaskDto fromParentTask(Task task) {

        var dto = new ParentTaskDto();
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
        return dto;
    }
}
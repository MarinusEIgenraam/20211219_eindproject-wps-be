package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Task.TaskInputDto;
import lombok.var;
import org.springframework.lang.Nullable;

import java.time.LocalDate;
import java.util.List;

public class ProjectInputDto {
    public Long projectId;
    public String projectName;
    public String url;
    public String imageUrl;
    public String description;
    public String username;
    public Long categoryId;
    public LocalDate startTime;
    @Nullable
    public LocalDate editedTime;
    public LocalDate endTime;
    public boolean publiclyVisible;
    public List<TaskInputDto> projectTaskList;
    public List<String> collaborators;

    public Project toProject() {
        var project = new Project();

        project.setProjectName(projectName);
        project.setUrl(url);
        project.setImageUrl(imageUrl);
        project.setDescription(description);
        project.setStartTime(startTime);
        project.setEditedTime(editedTime);
        project.setPubliclyVisible(publiclyVisible);
        project.setEndTime(endTime);

        return project;
    }
}
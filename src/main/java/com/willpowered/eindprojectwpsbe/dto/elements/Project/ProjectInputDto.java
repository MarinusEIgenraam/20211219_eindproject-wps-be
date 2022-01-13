package com.willpowered.eindprojectwpsbe.dto.elements.Project;

import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskInputDto;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

public class ProjectInputDto {
    public Long projectId;
    public String projectName;
    public String url;
    public String imageUrl;
    public String description;
    public String username;
    public Long categoryId;
    public LocalDateTime startTime;
    public LocalDateTime endTime;
    public Boolean publiclyVisible;
    public List<TaskInputDto> projectTaskList;
    public List<String> collaborators;

    public Project toProject() {
        var project = new Project();

        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setUrl(url);
        project.setImageUrl(imageUrl);
        project.setDescription(description);
        project.setStartTime(startTime);
        project.setEndTime(endTime);
        project.setPubliclyVisible(publiclyVisible);

        return project;
    }
}
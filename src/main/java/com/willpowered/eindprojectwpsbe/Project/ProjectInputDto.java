package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Task.TaskInputDto;
import lombok.var;

import java.time.Instant;
import java.util.List;

public class ProjectInputDto {
    public Long projectId;
    public String projectName;
    public String url;
    public String imageUrl;
    public String description;
    public Instant startTime;
    public Instant endTime;
    public Boolean publiclyVisible;
    public Long categoryId;
    public List<TaskInputDto> projectTaskList;

    public Project toProject() {
        var project = new Project();

        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setUrl(url);
        project.setImageUrl(imageUrl);
        project.setDescription(description);
//        project.setStartTime(startTime);
//        project.setEndTime(endTime);
//        project.setPubliclyVisible(publiclyVisible);

        return project;
    }
}
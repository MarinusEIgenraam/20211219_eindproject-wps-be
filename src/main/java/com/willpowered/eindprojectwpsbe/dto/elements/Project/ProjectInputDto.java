package com.willpowered.eindprojectwpsbe.dto.elements.Project;

import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskInputDto;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;

import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

public class ProjectInputDto {
    public Long projectId;
    public String projectName;
    public String url;
    public String description;
    public Instant startTime;
    public Instant endTime;
    public Boolean publiclyVisible;
    public Long categoryId;
    public String projectOwnerName;
    public List<Task> projectTaskList;

    public Project toProject() {
        var project = new Project();

        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setUrl(url);
        project.setDescription(description);
        project.setStartTime(startTime);
        project.setEndTime(endTime);
        project.setPubliclyVisible(publiclyVisible);
        project.setProjectTaskList(projectTaskList);

        return project;
    }
}
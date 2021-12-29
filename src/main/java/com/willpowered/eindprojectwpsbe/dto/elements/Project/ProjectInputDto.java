package com.willpowered.eindprojectwpsbe.dto.elements.Project;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import java.time.Instant;

public class ProjectInputDto {
    public Long projectId;
    public String projectName;
    public String url;
    public String description;
    public Instant startTime;
    public Instant endTime;
    public Integer voteCount;
    public Long categoryId;
    public String projectOwnerName;

    public Project toProject() {
        var project = new Project();

        project.setProjectId(projectId);
        project.setProjectName(projectName);
        project.setUrl(url);
        project.setDescription(description);
        project.setStartTime(startTime);
        project.setEndTime(endTime);
        project.setVoteCount(voteCount);

        return project;
    }
}
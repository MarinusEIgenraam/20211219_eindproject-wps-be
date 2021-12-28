package com.willpowered.eindprojectwpsbe.dto.elements.Project;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import java.time.Instant;

public class ProjectDTO {
    public Long projectId;
    public String projectName;
    public String url;
    public String description;
    public Instant startTime;
    public Instant endTime;
    public Integer voteCount;
    public Category category;
    public User projectOwner;

    public static ProjectDTO fromProject(Project project) {
        var dto = new ProjectDTO();
        dto.projectId = project.getProjectId();
        dto.projectName = project.getProjectName();
        dto.url = project.getUrl();
        dto.description = project.getDescription();
        dto.startTime = project.getStartTime();
        dto.endTime = project.getEndTime();
        dto.voteCount = project.getVoteCount();
        dto.category = project.getCategory();
        dto.projectOwner = project.getProjectOwner();

        return dto;
    }
}
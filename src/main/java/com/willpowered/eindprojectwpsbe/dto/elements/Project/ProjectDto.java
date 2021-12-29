package com.willpowered.eindprojectwpsbe.dto.elements.Project;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import java.time.Instant;

public class ProjectDto {
    public Long projectId;
    public String projectName;
    public String url;
    public String description;
    public Instant startTime;
    public Instant endTime;
    public Integer voteCount;
    public Category category;
    public User projectOwner;

    public static ProjectDto fromProject(Project project) {
        var Dto = new ProjectDto();
        Dto.projectId = project.getProjectId();
        Dto.projectName = project.getProjectName();
        Dto.url = project.getUrl();
        Dto.description = project.getDescription();
        Dto.startTime = project.getStartTime();
        Dto.endTime = project.getEndTime();
        Dto.voteCount = project.getVoteCount();
        Dto.category = project.getCategory();
        Dto.projectOwner = project.getProjectOwner();

        return Dto;
    }
}
package com.willpowered.eindprojectwpsbe.dto.elements.Project;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.dto.auth.User.UserDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Category.CategoryDto;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;

import java.time.Instant;
import java.util.List;



public class ProjectDto {

    @Nullable
    public Long projectId;
    public String projectName;
    public String url;
    public String imageUrl;
    public String description;
    public Instant startTime;
    public Instant endTime;
    public Integer voteCount;
    public Boolean publiclyVisible;
    public CategoryDto category;
    public UserDto projectOwner;
    public List<Task> projectTaskList;
    public Integer commentCount;

    public static ProjectDto fromProject(Project project) {

        var dto = new ProjectDto();
        dto.projectName = project.getProjectName();
        dto.url = project.getUrl();
        dto.imageUrl = project.getImageUrl();
        dto.description = project.getDescription();
//        dto.startTime = project.getStartTime();
//        dto.endTime = project.getEndTime();
//        dto.voteCount = project.getVoteCount();
//        dto.publiclyVisible = project.getPubliclyVisible();
//        dto.category = CategoryDto.fromCategory(project.getCategory());
//        dto.projectOwner = UserDto.fromUser(project.getProjectOwner());
//        dto.projectTaskList = project.getProjectTaskList();

        return dto;
    }
}
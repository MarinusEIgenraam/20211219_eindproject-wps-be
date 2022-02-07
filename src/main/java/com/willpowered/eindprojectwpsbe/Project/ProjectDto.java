package com.willpowered.eindprojectwpsbe.Project;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.Category.CategoryDto;
import com.willpowered.eindprojectwpsbe.Task.TaskDto;
import com.willpowered.eindprojectwpsbe.User.UserDto;
import lombok.var;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class ProjectDto {

    @Nullable
    public Long projectId;
    public String projectName;
    public String url;
    public String imageUrl;
    public String description;
    public LocalDate startTime;
    @Nullable
    public LocalDate editedTime;
    public LocalDate endTime;
    public Integer voteCount;
    public Boolean isRunning;
    public Boolean publiclyVisible;
    public CategoryDto category;
    public UserDto projectOwner;
    public List<TaskDto> projectTaskList;
    public List<UserDto> collaborators;

    public Integer commentCount;

    public static ProjectDto fromProject(Project project) {

        ModelMapper modelMapper = new ModelMapper();

        var dto = new ProjectDto();
        dto.projectId = project.getProjectId();
        dto.projectName = project.getProjectName();
        dto.url = project.getUrl();
        dto.imageUrl = project.getImageUrl();
        dto.description = project.getDescription();
        dto.startTime = project.getStartTime();
        dto.editedTime = project.getEditedTime();
        dto.endTime = project.getEndTime();
        dto.isRunning = project.getIsRunning();
        dto.voteCount = project.getVoteCount();
        dto.publiclyVisible = project.getPubliclyVisible();
        dto.category = CategoryDto.fromCategory(project.getCategory());
        dto.projectOwner = UserDto.fromUser(project.getProjectOwner());
        dto.projectTaskList = project.getProjectTaskList().stream().map(p -> TaskDto.fromTask(p)).collect(Collectors.toList());
        dto.collaborators = project.getCollaborators().stream().map(p -> UserDto.fromUser(p)).collect(Collectors.toList());

        return dto;
    }
}
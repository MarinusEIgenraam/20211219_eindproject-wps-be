package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Category.CategoryDto;
import com.willpowered.eindprojectwpsbe.User.UserDto;


public class ParentProjectDto extends ProjectDto {


    public static ParentProjectDto fromParentProject(Project project) {

        var dto = new ParentProjectDto();
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

        return dto;
    }
}
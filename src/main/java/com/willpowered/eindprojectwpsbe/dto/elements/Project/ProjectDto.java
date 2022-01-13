package com.willpowered.eindprojectwpsbe.dto.elements.Project;

import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.dto.auth.User.UserDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Category.CategoryDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskDto;
import com.willpowered.eindprojectwpsbe.mapping.MapperUtil;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.var;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class ProjectDto {

    @Autowired
    private ModelMapper modelMapper;
//    List<TaskDto> projectTaskDtoList = MapperUtil.mapList(projectTaskList, TaskDto.class)


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
    public List<Task> tasks;
    public List<TaskDto> projectTaskDtoList = MapperUtil.mapList(tasks, TaskDto.class) ;

    public Integer commentCount;

    public static ProjectDto fromProject(Project project) {

        var dto = new ProjectDto();
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
        dto.projectTaskDtoList = project.getProjectTaskList();

        return dto;
    }
}
package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectInputDto;
import com.willpowered.eindprojectwpsbe.exception.BadRequestException;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.service.communication.CommentService;
import com.willpowered.eindprojectwpsbe.service.elements.ProjectService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    @Autowired
    private ProjectService projectService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable("id") Long id) {
        var project = projectService.getProject(id);
        Integer commentCount = commentService.calculateComments(project);

        ProjectDto projectDto = ProjectDto.fromProject(project);
        projectDto.commentCount = commentCount;

        return projectDto;
    }

    @GetMapping("/all")
    public List<ProjectDto> getAllProjects() {
        var dtos = new ArrayList<ProjectDto>();
        List<Project> projects;

        projects = projectService.getAllProjects();
        for (Project project : projects) {
            dtos.add(ProjectDto.fromProject(project));
        }
        return dtos;
    }

    @GetMapping
    public List<ProjectDto> getProjects(
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "username", required = false) String username
    ) {
        var dtos = new ArrayList<ProjectDto>();

        List<Project> projects;
        if (categoryId != null && username == null) {
            projects = projectService.getProjectsForCategory(categoryId);
        } else if  (categoryId == null && username != null) {
            projects = projectService.getProjectsForProjectOwner(username);
        } else {
            throw new BadRequestException();
        }

        for (Project project : projects) {
            dtos.add(ProjectDto.fromProject(project));
        }

        return dtos;
    }

    @PostMapping
    public ProjectDto saveProject(@RequestBody ProjectInputDto dto) {
        Project project = projectService.saveProject(dto);
        return ProjectDto.fromProject(project);
    }

    @PutMapping("/{id}")
    public ProjectDto updateProject(@PathVariable Long id, @RequestBody Project project) {
        projectService.updateProject(id, project);
        return ProjectDto.fromProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
    }
}
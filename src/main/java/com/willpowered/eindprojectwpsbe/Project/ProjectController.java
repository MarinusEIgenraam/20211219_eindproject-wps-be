package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Comment.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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


    //////////////////////////////
    //// Create

    @PostMapping
    public ProjectDto saveProject(@RequestBody ProjectInputDto dto) {
        Project project = projectService.saveProject(dto);
        return ProjectDto.fromProject(project);
    }

    //////////////////////////////
    //// Read

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable("id") Long id) {
        var project = projectService.getProject(id);
        Integer commentCount = commentService.calculateComments(project);

        ProjectDto projectDto = ProjectDto.fromProject(project);
        projectDto.commentCount = commentCount;

        return projectDto;
    }

    @GetMapping("/all")
    public Page<ProjectDto> getAllProjects(
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "id,projectName", required = false) String[] sort) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        var dtos = new ArrayList<ProjectDto>();
        List<Project> projects;

        projects = projectService.getAllProjects(pageable);
        for (Project project : projects) {
            dtos.add(ProjectDto.fromProject(project));
        }

        Page<ProjectDto> pageOfProjects = new PageImpl<>(dtos);

        return pageOfProjects;
    }

    @GetMapping
    public Page<ProjectDto> getProjects(
            @RequestParam(value = "categoryId", required = false) Long categoryId,
            @RequestParam(value = "collaborator", required = false) String collaborator,
            @RequestParam(value = "projectOwner", required = false) String projectOwner,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "number", defaultValue = "0") int number,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "voteCount", required = false) String[] sort
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        List<Project> projects;
        var dtos = new ArrayList<ProjectDto>();

        if (categoryId != null && projectOwner == null && collaborator == null) {
            projects = projectService.getProjectsForCategory(categoryId, pageable);
        } else if (categoryId == null && projectOwner != null && collaborator == null) {
            projects = projectService.getProjectsForProjectOwner(projectOwner, pageable);
        } else if (categoryId == null && projectOwner == null && collaborator != null) {
            projects = projectService.getProjectsForProjectCollaborator(collaborator, pageable);
        } else {
            projects = projectService.getAllProjects(pageable);
        }

        for (Project project : projects) {
            dtos.add(ProjectDto.fromProject(project));
        }
        Page<ProjectDto> pageOfProjects = new PageImpl<>(dtos);

        return pageOfProjects;
    }

    //////////////////////////////
    //// Update

    @PutMapping("/{id}")
    public ProjectDto updateProject(@PathVariable Long id, @RequestBody Project project) {
        projectService.updateProject(id, project);
        return ProjectDto.fromProject(project);
    }

    //////////////////////////////
    //// Delete

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
    }
}
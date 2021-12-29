package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectInputDto;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.service.elements.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ProjectDto getProject(@PathVariable("id") Long id) {
        var project = projectService.getProject(id);
        return ProjectDto.fromProject(project);
    }

    @GetMapping
    public List<ProjectDto> getProjects() {
        var dtos = new ArrayList<ProjectDto>();
        var projects = projectService.getProjects();

        for (Project project : projects) {
            dtos.add(ProjectDto.fromProject(project));
        }
        return dtos;
    }

    @PostMapping
    public ProjectDto saveProject(@RequestBody ProjectInputDto Dto) {
        Project project = projectService.saveProject(Dto.toProject());
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
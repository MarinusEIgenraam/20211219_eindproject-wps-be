package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectDTO;
import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectInputDTO;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.service.elements.ProjectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
@Slf4j
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @GetMapping("/{id}")
    public ProjectDTO getProject(@PathVariable("id") Long id) {
        var project = projectService.getProject(id);
        return ProjectDTO.fromProject(project);
    }

    @PostMapping
    public ProjectDTO saveProject(@RequestBody ProjectInputDTO dto) {
        var project = projectService.saveProject(dto.toProject());
        return ProjectDTO.fromProject(project);
    }

    @PutMapping("/{id}")
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody Project project) {
        projectService.updateProject(id, project);
        return ProjectDTO.fromProject(project);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable("id") Long id) {
        projectService.deleteProject(id);
    }
}
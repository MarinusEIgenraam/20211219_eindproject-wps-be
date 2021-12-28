package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectResponse;
import com.willpowered.eindprojectwpsbe.service.elements.ProjectService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@RequestMapping("/projects")
@AllArgsConstructor
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping
    public ResponseEntity<Void> createProject(@RequestBody ProjectRequest projectRequest) {
        projectService.save(projectRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProjectResponse>> getAllProjects() {
        return status(HttpStatus.OK).body(projectService.getAllProjects());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjectResponse> getProject(@PathVariable Long id) {
        return status(HttpStatus.OK).body(projectService.getProject(id));
    }

    @GetMapping("by-category/{id}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByCategory(Long id) {
        return status(HttpStatus.OK).body(projectService.getProjectsByCategory(id));
    }

    @GetMapping("by-user/{name}")
    public ResponseEntity<List<ProjectResponse>> getProjectsByUsername(@PathVariable String name) {
        return status(HttpStatus.OK).body(projectService.getProjectsByUsername(name));
    }

}

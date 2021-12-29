package com.willpowered.eindprojectwpsbe.service.elements;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.CategoryRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;


    public Project saveProject(Project project) {
        return projectRepository.save(project);
    }

    public Project getProject(Long id) {
        Optional<Project> project = projectRepository.findById(id);

        if(project.isPresent()) {
            return project.get();
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Project> getProjects() {
        return projectRepository.findAll();
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public void updateProject(Long id, Project project) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            projectRepository.deleteById(id);
            projectRepository.save(project);
        } else {
            throw new RecordNotFoundException("project does not exist");
        }
    }

    public void partialUpdateProject(Long id, Project project) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isPresent()) {
            Project storedProject = projectRepository.findById(id).orElse(null);

            if (project.getProjectName()!=null && !project.getProjectName().isEmpty()) {
                storedProject.setProjectName(project.getProjectName());
            }
            if (project.getProjectOwner()!=null && !(project.getProjectOwner() == null)) {
                storedProject.setProjectOwner(project.getProjectOwner());
            }
            if (project.getDescription()!=null && !project.getDescription().isEmpty()) {
                storedProject.setDescription(project.getDescription());
            }
            projectRepository.save(storedProject);

        }
        else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }
}

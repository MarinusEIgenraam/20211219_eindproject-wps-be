package com.willpowered.eindprojectwpsbe.service.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectResponse;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.mapping.ProjectMapper;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
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

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;
    @Autowired
    private ProjectMapper projectMapper;

    public void save(ProjectRequest projectRequest) {
        Category category = categoryRepository.findByName(projectRequest.getCategoryName())
                .orElseThrow(() -> new RecordNotFoundException(projectRequest.getCategoryName()));
        projectRepository.save(projectMapper.map(projectRequest, category, userAuthenticateService.getCurrentUser()));
    }

    @Transactional(readOnly = true) // Minder ballast voor server door data verkeer te verkleinen
    public ProjectResponse getProject(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id.toString()));
        return projectMapper.mapToDto(project);
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<ProjectResponse> getProjectsByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RecordNotFoundException(categoryId.toString()));
        List<Project> projects = projectRepository.findAllByCategory(category);
        return projects.stream().map(projectMapper::mapToDto).collect(toList());
    }
}

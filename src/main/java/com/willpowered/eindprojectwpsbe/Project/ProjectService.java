package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Category.Category;
import com.willpowered.eindprojectwpsbe.Category.CategoryRepository;
import com.willpowered.eindprojectwpsbe.Comment.CommentRepository;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Task.Task;
import com.willpowered.eindprojectwpsbe.Task.TaskInputDto;
import com.willpowered.eindprojectwpsbe.Task.TaskRepository;
import com.willpowered.eindprojectwpsbe.Task.TaskService;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserService userService;

    //////////////////////////////
    //// Create

    public Project saveProject(@NotNull ProjectInputDto projectInputDto) {
        var optionalCategory = categoryRepository.findById(projectInputDto.categoryId);
        Project project = projectInputDto.toProject();

        if (!optionalCategory.isPresent()) {
            throw new RecordNotFoundException("This category does not exist");
        }
        Category category = optionalCategory.get();
        project.setCategory(category);
        project.setProjectOwner(userService.getCurrentUser());

        if (projectInputDto.startTime == null) {
            project.setStartTime(LocalDate.now());
        } else {
            project.setStartTime(projectInputDto.startTime);
            project.setEditedTime(LocalDate.now());
        }

        List<Task> newTaskList = new ArrayList<>();

        Set<User> optionalCollaborators = new HashSet<>();
        if (projectInputDto.collaborators != null) {

            for (String username : projectInputDto.collaborators) {
                var optionalUser = userRepository.findById(username);
                optionalCollaborators.add(optionalUser.get());
            }
        }

        project.setCollaborators(optionalCollaborators);
        Project newProject = projectRepository.save(project);

        for (TaskInputDto dto : projectInputDto.projectTaskList) {
            if (dto.taskOwner == null) {
                dto.taskOwner = userService.getCurrentUser().getUsername();
            }
            dto.parentProjectId = newProject.getProjectId();
            newTaskList.add(taskService.saveTask(dto));
        }
        newProject.setProjectTaskList(newTaskList);
        return projectRepository.save(newProject);
    }

    //////////////////////////////
    //// Read

    public List<Project> getAllProjects(Pageable pageable) {
        Authentication authentication = authenticationService.getCurrentUser();
        User user = null;


        if (!authenticationService.isLoggedIn()) {
            return projectRepository.findAllViewableProjects(user, pageable);
        } else if (authenticationService.isLoggedIn() && authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return projectRepository.findAll();
        } else {
            user = userService.getCurrentUser();
            return projectRepository.findAllViewableProjects(user, pageable);
        }
    }

    public Project getProject(Long projectId) {
        var project = projectRepository.findById(projectId);

        if (project.isPresent()) {
            return project.get();
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Project> getProjectsForCategory(Long categoryId, Pageable pageable) {
        var optionalCategory = categoryRepository.findById(categoryId);
        Authentication authentication = authenticationService.getCurrentUser();

        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            User currentUser = null;
            if (authentication != null){
                currentUser = userService.getCurrentUser();
            }
            return projectRepository.findAllByCategory(categoryId, currentUser, pageable);
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    public List<Project> getProjectsForProjectOwner(String username, Pageable pageable) {
        var optionalUser = userRepository.findById(username);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return projectRepository.findAllByProjectOwner(user, pageable);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public List<Project> getProjectsForProjectCollaborator(String username, Pageable pageable) {
        var optionalUser = userRepository.findById(username);
        Authentication authentication = authenticationService.getCurrentUser();

        if (optionalUser.isPresent()) {
            User collaborator = optionalUser.get();
            User currentUser = null;
            if (authentication != null){
                currentUser = userService.getCurrentUser();
            }
            return projectRepository.findAllByCollaborators(collaborator, currentUser,pageable);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    //////////////////////////////
    //// Update

    public void updateProject(Long id, Project project) {
        var optionalProject = projectRepository.findById(id);

        if (optionalProject.isPresent()) {
            Project newProject = optionalProject.get();
            if (newProject.getProjectTaskList().equals(project.getProjectTaskList())) {
                projectRepository.save(project);
            } else {
                Project updatedProject = projectRepository.save(project);
                List newTaskList = new ArrayList<>();

                for (Task task : project.getProjectTaskList()) {
                    if (taskRepository.findById(task.getTaskId()).isPresent()) {

                        newTaskList.add(taskRepository.save(task));
                    }
                }

                updatedProject.setProjectTaskList((newTaskList));
                projectRepository.save(updatedProject);
            }
        } else {
            throw new RecordNotFoundException("Task does not exist");
        }
    }

    //////////////////////////////
    //// Delete

    public void deleteProject(Long id) {
        var optionalProject = projectRepository.findById(id);
        if (optionalProject.isPresent()) {
            projectRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }
}

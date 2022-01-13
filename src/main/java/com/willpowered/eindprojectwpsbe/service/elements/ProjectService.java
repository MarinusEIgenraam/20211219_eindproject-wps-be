package com.willpowered.eindprojectwpsbe.service.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectInputDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskInputDto;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.CommentRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.CategoryRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.TaskRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    private UserAuthenticateService userAuthenticateService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskRepository taskRepository;

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Project saveProject(@NotNull ProjectInputDto projectInputDto) {
        var optionalCategory = categoryRepository.findById(projectInputDto.categoryId);
        Project project = new Project();

        if (!optionalCategory.isPresent()) {
            throw new RecordNotFoundException("This category does not exist");
        }
        Category category = optionalCategory.get();

        project.setPubliclyVisible(projectInputDto.publiclyVisible);
        project.setProjectOwner(userAuthenticateService.getCurrentUser());
        project.setUrl(projectInputDto.url);
        project.setProjectName(projectInputDto.projectName);
        project.setDescription(projectInputDto.description);
        project.setCategory(category);
        project.setEndTime(projectInputDto.endTime);
        if (projectInputDto.startTime == null) {
            project.setStartTime(LocalDate.now());
        } else {
            project.setStartTime(projectInputDto.startTime);
            project.setEditedTime(LocalDate.now());
        }

        List<Task> newTaskList = new ArrayList<>();

        List<User> optionalCollaborators = new ArrayList<>();
        if (projectInputDto.collaborators != null) {

            for (String username : projectInputDto.collaborators) {
                var optionalUser = userRepository.findById(username);
                optionalCollaborators.add(optionalUser.get());
            }
        }

        project.setCollaborators(optionalCollaborators);
        Project newProject = projectRepository.save(project);

        for (TaskInputDto dto : projectInputDto.projectTaskList) {
            if (dto.taskOwnerName == null) {
                dto.taskOwnerName = userAuthenticateService.getCurrentUser().getUsername();
            }
            dto.parentProjectId = newProject.getProjectId();
            newTaskList.add(taskService.saveTask(dto));
        }
        newProject.setProjectTaskList(newTaskList);
        return newProject;
    }

    public void updateProject(Long id, Project project) {
        Optional<Project> optionalProject = projectRepository.findById(id);

        if (optionalProject.isPresent()) {
            Project newProject = optionalProject.get();
            if (newProject.getProjectTaskList().equals(project.getProjectTaskList())) {
                projectRepository.deleteById(id);
                projectRepository.save(project);
            } else {
                projectRepository.deleteById(id);

                Project updatedProject = projectRepository.save(project);
                List newTaskList = new ArrayList<>();

                for (Task task : project.getProjectTaskList()) {
                    if (taskRepository.findById(task.getTaskId()).isPresent()) {
                        taskRepository.deleteById(task.getTaskId());

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

//    public void updateProject(Long id, Project project) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        if (optionalTask.isPresent()) {
//            projectRepository.deleteById(id);
//            projectRepository.save(project);
//        } else {
//            throw new RecordNotFoundException("Task does not exist");
//        }
//    }

//    public Task addProjectTask(TaskInputDto dto, Long projectId) {
//        var project = projectRepository.findById(projectId).orElse(null);
//        Task task = new Task();
//        if (project != null) {
//            task.setDescription(dto.description);
//            task.setTaskName(dto.taskName);
//            task.setTaskOwner(userAuthenticateService.getCurrentUser());
//            task.setParentProject(project);
//            task.setStartTime(dto.startTime);
//            task.setEndTime(dto.endTime);
//            task.setIsRunning(dto.isRunning);
//        } else {
//            throw new RecordNotFoundException("Project does not exist");
//        }
//
//        List<Task> projectTaskList = project.getProjectTaskList();
//        projectTaskList.add(task);
//        project.setProjectTaskList(projectTaskList);
//        projectRepository.save(project);
//
//        return taskRepository.save(task);
//    }

    public Project getProject(Long projectId) {
        Optional<Project> project = projectRepository.findById(projectId);

        if (project.isPresent()) {
            return project.get();
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Project> getProjectsForCategory(Long categoryId) {
        var optionalCategory = categoryRepository.findById(categoryId);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            User user = userAuthenticateService.getCurrentUser();
            return projectRepository.findAllByCategory(category);
        } else {
            throw new RecordNotFoundException("Category does not exist");
        }
    }

    public List<Project> getProjectsForProjectOwner(String username) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return projectRepository.findAllByProjectOwner(user);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public List<Project> getProjectsForProjectCollaborator(String username) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return projectRepository.findAllByCollaborators(user);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}

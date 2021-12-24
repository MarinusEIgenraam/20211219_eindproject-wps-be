package com.willpowered.eindprojectwpsbe.service.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskResponse;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.mapping.TaskMapper;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.CategoryRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.TaskRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class TaskService {

    @Autowired
    private final TaskRepository taskRepository;
    @Autowired
    private final ProjectRepository projectRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private final CategoryRepository categoryRepository;
    @Autowired
    private final UserAuthenticateService userAuthenticateService;
    @Autowired
    private final TaskMapper taskMapper;


    public void save(TaskRequest taskRequest) {
        Project project = projectRepository.findByProjectName(taskRequest.getParentProjectName())
                .orElseThrow(() -> new RecordNotFoundException(taskRequest.getParentProjectName()));
        Task task = taskRepository.findByTaskName(taskRequest.getParentTaskName())
                .orElseThrow(() -> new RecordNotFoundException(taskRequest.getParentTaskName()));
        taskRepository.save(taskMapper.map(taskRequest, task, project, userAuthenticateService.getCurrentUser()));
    }

    @Transactional(readOnly = true)
    public TaskResponse getTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RecordNotFoundException(id.toString()));
        return taskMapper.mapToDto(task);
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskMapper::mapToDto)
                .collect(toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksByProject(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RecordNotFoundException(projectId.toString()));
        List<Task> tasks = taskRepository.findAllByParentProject(project);
        return tasks.stream().map(taskMapper::mapToDto).collect(toList());
    }

    @Transactional(readOnly = true)
    public List<TaskResponse> getTasksTaskOwner(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        return taskRepository.findByTaskOwner(user)
                .stream()
                .map(taskMapper::mapToDto)
                .collect(toList());
    }
}



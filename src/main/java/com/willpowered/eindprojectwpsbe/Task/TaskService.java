package com.willpowered.eindprojectwpsbe.Task;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.auth.Models.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;



    public Task getTask(Long taskId) {
        Optional<Task> task = taskRepository.findById(taskId);

        if (task.isPresent()) {
            return task.get();
        } else {
            throw new RecordNotFoundException("Task does not exist");
        }
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksForParentProject(Long projectId) {
        var optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return taskRepository.findAllByParentProject(project);
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Task> getTasksForTaskOwner(String username) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return taskRepository.findAllByTaskOwner(user);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public List<Task> getTasksForParentTask(Long parentTaskId) {
        var optionalTask = taskRepository.findById(parentTaskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            return taskRepository.findAllByParentTask(task);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public Task saveTask(TaskInputDto dto) {
        Task task = new Task();

        if (dto.parentProjectId != null && dto.parentTaskId != null) {
            task.setParentTask(taskRepository.findById(dto.parentTaskId).get());
            task.setParentProject(projectRepository.findById(dto.parentProjectId).get());
        } else if (dto.parentProjectId == null && dto.parentTaskId != null) {
            task.setParentTask(taskRepository.findById(dto.parentTaskId).get());
        } else if (dto.parentProjectId != null && dto.parentTaskId == null) {
            task.setParentProject(projectRepository.findById(dto.parentProjectId).get());
        } else {
            throw new RecordNotFoundException("No parent found");
        }
        User currentUser = userAuthenticateService.getCurrentUser();

        if (dto.taskOwnerName != null) {
            task.setTaskOwner(userRepository.findByUsername(dto.taskOwnerName).get());
        } else if (dto.taskOwnerName == null && currentUser == null) {
            throw new UserNotFoundException("No user");
        } else {
            task.setTaskOwner(currentUser);
        }

        task.setTaskId(dto.taskId);
        task.setTaskName(dto.taskName);
        task.setIsRunning(true);
        task.setDescription((dto.description));
        task.setStartTime(dto.startTime);
        task.setEndTime(dto.endTime);

        return taskRepository.save(task);
    }

    public void updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            taskRepository.save(task);
        } else {
            throw new RecordNotFoundException("task does not exist");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}



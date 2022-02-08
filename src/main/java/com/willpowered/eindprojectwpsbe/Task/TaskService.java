package com.willpowered.eindprojectwpsbe.Task;

import com.willpowered.eindprojectwpsbe.Alert.AlertService;
import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
    ProjectRepository projectRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AlertService alertService;
    @Autowired
    private UserService userService;


    //////////////////////////////
    //// Create

    public Task saveTask(TaskInputDto dto) {
        Task task = saveTaskData(dto);

        if (dto.parentTaskId == null && dto.parentProjectId != null) {
            var projectOptional = projectRepository.findById(dto.parentProjectId);
            if (projectOptional.isPresent()) {
                task.setParentProject(projectOptional.get());
            }
        } else if (dto.parentTaskId != null && dto.parentProjectId == null) {
            var taskOptional = taskRepository.findById(dto.parentTaskId);
            if (taskOptional.isPresent()) {
                task.setParentTask(taskOptional.get());
            }
        } else {
            throw new RecordNotFoundException("No parent was found");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (dto.taskOwner != null) {
            var optionalTaskOwner = userRepository.findById(dto.taskOwner);
            if (optionalTaskOwner.isPresent()) {
                User taskOwner = optionalTaskOwner.get();
                task.setTaskOwner(taskOwner);
            }
        } else {
            User currentUser = userService.getCurrentUser();
            task.setTaskOwner(currentUser);
        }

        Task newTask = taskRepository.save(task);

        if (dto.taskTaskList != null) {
            List<Task> newTaskList = new ArrayList<>();

            for (TaskInputDto taskTaskInputDto : dto.taskTaskList) {
                taskTaskInputDto.parentTaskId = newTask.getTaskId();
                newTaskList.add(saveTask(taskTaskInputDto));
            }

            newTask.setTaskTaskList(newTaskList);
        }
        alertService.addAlert("Task invitation", newTask.getTaskOwner());

        return taskRepository.save(newTask);
    }

    public void updateTask(Long id, Task task) {
        var optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task newTask = optionalTask.get();
            if (newTask.getTaskTaskList().equals(task.getTaskTaskList())) {
                return;
            } else {
                Task updatedTask = taskRepository.save(task);
            }
        } else {
            throw new RecordNotFoundException("Task does not exist");
        }
    }

    public Task saveTaskData(TaskInputDto taskInputdto) {
        Task task = new Task();

        if (taskInputdto.taskOwner != null) {
            var optionalUser = userRepository.findById(taskInputdto.taskOwner);
            if (optionalUser.isPresent()) {
                task.setTaskOwner(optionalUser.get());
            }
        }
        task.setDescription(taskInputdto.description);
        task.setTaskName(taskInputdto.taskName);
        task.setEndTime(taskInputdto.endTime);
        if (taskInputdto.startTime == null) {
            task.setStartTime(LocalDate.now());
        } else {
            task.setStartTime(taskInputdto.startTime);
            task.setEditedTime(LocalDate.now());
        }

        return task;
    }


    //////////////////////////////
    //// Read

    public TaskDto getTask(Long taskId) {
        var optionalTask = taskRepository.findById(taskId);

        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            return TaskDto.fromTask(task);
        } else {
            throw new RecordNotFoundException("Task does not exist");
        }
    }

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksForParentProject(Long projectId) {
        var optionalProject = projectRepository.findById(projectId);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            User currentUser = null;
            if (authentication != null){
                currentUser = userService.getCurrentUser();
            }
            return taskRepository.findAllByParentProject(project, currentUser);
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Task> getTasksForTaskOwner(String username) {
        var optionalUser = userRepository.findById(username);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            User currentUser = null;
            if (authentication != null){
                currentUser = userService.getCurrentUser();
            }
            return taskRepository.findAllByTaskOwner(user, currentUser);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public List<Task> getTasksForParentTask(Long parentTaskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var optionalTask = taskRepository.findById(parentTaskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            User currentUser = null;
            if (authentication != null){
                currentUser = userService.getCurrentUser();
            }
            return taskRepository.findAllByParentTask(task, currentUser);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    //////////////////////////////
    //// Update

    //////////////////////////////
    //// Delete
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}



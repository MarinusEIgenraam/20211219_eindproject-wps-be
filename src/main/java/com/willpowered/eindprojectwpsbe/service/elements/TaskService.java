package com.willpowered.eindprojectwpsbe.service.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskInputDto;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.exception.UserNotFoundException;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.TaskRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
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


    public Task saveTask(TaskInputDto dto) {
        Task task = saveTaskData(dto);

        if (dto.parentProjectId == null && dto.parentTaskId != null) {
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

        Task parentTask = taskRepository.save(task);


        if (dto.taskTaskList != null) {
            List<Task> newTaskList = new ArrayList<>();

            for (TaskInputDto taskTaskInputDto : dto.taskTaskList) {
                taskTaskInputDto.parentTaskId = parentTask.getTaskId();
                newTaskList.add(saveTask(taskTaskInputDto));
            }



            parentTask.setTaskTaskList(newTaskList);
        }

        return parentTask;
    }

    public void updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);

        if (optionalTask.isPresent()) {
            Task newTask = optionalTask.get();
            if (newTask.getTaskTaskList().equals(task.getTaskTaskList())) {
                taskRepository.deleteById(id);
                taskRepository.save(task);
            } else {
                taskRepository.deleteById(id);

                Task updatedTask = taskRepository.save(task);
                List newTaskList = new ArrayList<>();

                for (Task subTask : task.getTaskTaskList()) {
                    if (taskRepository.findById(subTask.getTaskId()).isPresent()) {
                        taskRepository.deleteById(subTask.getTaskId());

                        newTaskList.add(taskRepository.save(subTask));
                    }
                }

                updatedTask.setTaskTaskList((newTaskList));
                taskRepository.save(updatedTask);
            }
        } else {
            throw new RecordNotFoundException("Task does not exist");
        }
    }

    public Task saveTaskData(TaskInputDto taskInputdto) {
        Task task = new Task();

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

    public TaskDto getTask(Long taskId) {
        Optional<Task> optionalTask = taskRepository.findById(taskId);

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
        User currentUser = userAuthenticateService.getCurrentUser();
        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return taskRepository.findAllByParentProject(project, currentUser);
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Task> getTasksForTaskOwner(String username) {
        var optionalUser = userRepository.findById(username);
        User currentUser = userAuthenticateService.getCurrentUser();

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return taskRepository.findAllByTaskOwner(user, currentUser);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public List<Task> getTasksForParentTask(Long parentTaskId) {
        User currentUser = userAuthenticateService.getCurrentUser();

        var optionalTask = taskRepository.findById(parentTaskId);
        if (optionalTask.isPresent()) {
            Task task = optionalTask.get();
            return taskRepository.findAllByParentTask(task, currentUser);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}



package com.willpowered.eindprojectwpsbe.Task;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.exception.UserNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (dto.taskOwnerName != null) {
            task.setTaskOwner(userRepository.findByUsername(dto.taskOwnerName).get());
        } else if (dto.taskOwnerName == null && authentication == null) {
            throw new UserNotFoundException("No user");
        } else {
            User currentUser = userAuthenticateService.getCurrentUser();
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
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            User currentUser = null;
            if (authentication != null){
                currentUser = userAuthenticateService.getCurrentUser();
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
                currentUser = userAuthenticateService.getCurrentUser();
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
                currentUser = userAuthenticateService.getCurrentUser();
            }
            return taskRepository.findAllByParentTask(task, currentUser);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

}



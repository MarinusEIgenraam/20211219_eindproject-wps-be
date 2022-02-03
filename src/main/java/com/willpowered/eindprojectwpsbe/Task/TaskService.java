package com.willpowered.eindprojectwpsbe.Task;

import com.willpowered.eindprojectwpsbe.Alert.AlertService;
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
import java.util.stream.Collectors;

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
    @Autowired
    private AlertService alertService;


    public Task saveTask(TaskInputDto dto) {
        Task task = saveTaskData(dto);
        Optional<Task> optionalParentTask = null;
        Optional<Project> optionalParentProject = null;
        Task taskParentTask = new Task();
        Project taskParentProject = new Project();

        if (dto.parentTaskId == null && dto.parentProjectId != null) {
            optionalParentProject = projectRepository.findById(dto.parentProjectId);
            if (optionalParentProject.isPresent()) {
                taskParentProject = optionalParentProject.get();
                task.setParentProject(taskParentProject);
            }
        } else if (dto.parentTaskId != null && dto.parentProjectId == null) {
            optionalParentTask = taskRepository.findById(dto.parentTaskId);
            if (optionalParentTask.isPresent()) {
                taskParentTask = optionalParentTask.get();
                task.setParentTask(taskParentTask);
            }
        } else {
            throw new RecordNotFoundException("No parent was found");
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();


        if (dto.taskOwner != null) {
            Optional<User> optionalTaskOwner = userRepository.findByUsername(dto.taskOwner);
            if (optionalTaskOwner.isPresent()) {
                User taskOwner = optionalTaskOwner.get();
                task.setTaskOwner(taskOwner);
            }
        } else {
            User currentUser = userAuthenticateService.getCurrentUser();
            task.setTaskOwner(currentUser);
        }

        Task newTask = taskRepository.save(task);
        if (newTask.getParentProject() != null && newTask.getParentTask() == null) {
            if(taskParentProject.getProjectTaskList() ==  null) {
                List<Task> newTaskList = new ArrayList();
                newTaskList.add(newTask);
                taskParentProject.setProjectTaskList(newTaskList);
            } else {
                taskParentProject.getProjectTaskList().add(newTask);
            }
            projectRepository.save(taskParentProject);
        } else {
            taskParentTask.getTaskTaskList().add(newTask);
            taskRepository.save(taskParentTask);
        }

        if (dto.taskTaskList != null) {
            List<Task> newTaskList = new ArrayList<>();

            for (TaskInputDto taskTaskInputDto : dto.taskTaskList) {
                taskTaskInputDto.parentTaskId = newTask.getTaskId();
                newTaskList.add(saveTask(taskTaskInputDto));
            }


            newTask.setTaskTaskList(newTaskList);
        }
        alertService.addAlert("Task invitation", newTask.getTaskOwner());

        return newTask;
    }

    public void updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);

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
//
//    public void updateTask(Long id, TaskInputDto taskInputDto) {
//        Task newTask = taskInputDto.toTask();
//        Optional<Task> optionalTask = taskRepository.findById(id);
//
//        if (taskInputDto.parentTaskId != null && taskInputDto.parentProjectId == null) {
//            Optional<Task> optionalParentTask = taskRepository.findById(taskInputDto.parentTaskId);
//            optionalParentTask.ifPresent(newTask::setParentTask);
//        } else if (taskInputDto.parentTaskId == null && taskInputDto.parentProjectId != null) {
//            Optional<Project> optionalParentProject = projectRepository.findById(taskInputDto.parentProjectId);
//            optionalParentProject.ifPresent(newTask::setParentProject);
//        }
//
//        if (optionalTask.isPresent()) {
//            Task loadedTask = optionalTask.get();
//            if (!newTask.equals(loadedTask) && newTask.getTaskTaskList().equals(loadedTask.getTaskTaskList())) {
//                Task updatedTask = taskRepository.save(newTask);
//            } else if (!newTask.equals(loadedTask) && !newTask.getTaskTaskList().equals(loadedTask.getTaskTaskList())) {
//                if (taskInputDto.taskTaskList != null) {
//                    taskInputDto.taskTaskList.forEach(p -> updateTask(p.taskId, p));
//                }
//            }
//        } else {
//            saveTask(taskInputDto);
//        }
//    }



//
//    public void updateTask(Long id, TaskInputDto taskInputDto) {
//        Optional<Task> optionalTask = taskRepository.findById(id);
//        Task updatedTask = taskInputDto.toTask();
//        updatedTask.setTaskOwner(userRepository.findById(taskInputDto.taskOwnerName).get());
//        if (taskInputDto.parentTaskId != null) {
//            updatedTask.setParentTask(taskRepository.findById(taskInputDto.parentTaskId).get());
//        }
//        if (taskInputDto.parentProjectId != null) {
//            updatedTask.setParentTask(taskRepository.findById(taskInputDto.parentProjectId).get());
//        }
//
//        if (optionalTask.isPresent()) {
//            Task newTask = optionalTask.get();
//            if (newTask.getTaskTaskList().equals(updatedTask.getTaskTaskList())) {
//                taskRepository.save(updatedTask);
//            } else {
//                Optional<User> optionalUser = userRepository.findById(task.getTaskOwner().getUsername());
//            }
//        } else {
//            throw new RecordNotFoundException("Task does not exist");
//        }
//
//    }
    public Task saveTaskData(TaskInputDto taskInputdto) {
        Task task = new Task();

        if (taskInputdto.taskOwner != null) {
            Optional<User> optionalUser = userRepository.findById(taskInputdto.taskOwner);
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



package com.willpowered.eindprojectwpsbe.Task;

import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    UserRepository userRepository;
    @Mock
    TaskRepository taskRepository;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserService userService;
    @Mock
    AuthenticationService authenticationService;

    @InjectMocks
    private TaskService taskService;
    @Captor
    ArgumentCaptor<Task> projectCaptor;

    @Mock
    User user;
    @Mock
    Project project;
    @Mock
    TaskInputDto taskInputDto;
    @Mock
    TaskDto taskDto;
    @Mock
    Authentication authentication;

    private Task firstTask;
    private Task secondTask;
    private Task firstSubtask;
    private Task secondSubTask;
    private TaskInputDto firstTaskInputDto;
    private TaskInputDto secondTaskInputDto;
    private List<Task> firstTaskList;

    @BeforeEach
    void setUp() {
        firstTaskInputDto = TaskInputDto.builder()
                .description("First description")
                .parentProjectId(1L)
                .taskName("First dto name")
                .endTime(LocalDate.now())
                .taskOwner("null")
                .build();
        secondTaskInputDto = TaskInputDto.builder()
                .description("Second description")
                .parentTaskId(1L)
                .taskName("Second dto name")
                .endTime(LocalDate.now())
                .taskOwner("null")
                .build();
        firstSubtask = Task.builder()
                .taskId(3L)
                .description("Third description")
                .parentTask(firstTask)
                .endTime(LocalDate.now())
                .taskName("Third name")
                .taskOwner(user)
                .isRunning(true)
                .build();
        secondSubTask = Task.builder()
                .taskId(4L)
                .description("Fourth description")
                .parentTask(secondTask)
                .endTime(LocalDate.now())
                .taskName("Fourth name")
                .taskOwner(user)
                .isRunning(true)
                .build();
        firstTaskList = List.of(firstSubtask);
        List<Task> secondTaskList = List.of(secondSubTask);
        firstTask = Task.builder()
                .taskId(1L)
                .description("First description")
                .parentProject(project)
                .taskName("First name")
                .endTime(LocalDate.now())
                .taskTaskList(firstTaskList)
                .taskOwner(user)
                .isRunning(true)
                .build();
        secondTask = Task.builder()
                .taskId(2L)
                .description("Second description")
                .parentProject(project)
                .taskName("Second name")
                .taskOwner(user)
                .taskTaskList(secondTaskList)
                .isRunning(true)
                .endTime(LocalDate.now())
                .build();


    }

    //////////////////////////////
    //// Create

//    @Test
//    void saveTaskWithParentProject() {
//        when(projectRepository.findById(any())).thenReturn(Optional.ofNullable(project));
//        when(taskRepository.findById(any())).thenReturn(Optional.ofNullable(firstTask));
//        when(userRepository.findById("null")).thenReturn(Optional.ofNullable(user));
//        when(userService.getCurrentUser()).thenReturn(user);
//        when(taskRepository.save(any())).thenReturn(firstTask);
//        when(taskService.saveTaskData(firstTaskInputDto)).thenReturn(firstTask);
//
//        Task savedTask = taskService.saveTask(firstTaskInputDto);
//
//        verify(taskRepository, times(2)).save(any());
//    }
//
//    @Test
//    void saveTaskWithParentTask() {
//        when(taskService.saveTaskData(secondTaskInputDto)).thenReturn(firstTask);
//        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(firstTask));
//        when(userRepository.findById("null")).thenReturn(Optional.ofNullable(user));
//        when(userService.getCurrentUser()).thenReturn(user);
//        when(taskRepository.save(any())).thenReturn(secondTask);
//
//        Task savedTask = taskService.saveTask(secondTaskInputDto);
//
//
//    }

    @Test
    void saveTaskData() {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));

        Task newTask = taskService.saveTaskData(firstTaskInputDto);

        verify(userRepository, times(1)).findById(any());
        assertThat(newTask.getTaskName()).isEqualTo(firstTaskInputDto.taskName);
    }

    //////////////////////////////
    //// Read

//    @Test
//    void getTask() {
//        when(taskRepository.findById(firstTask.getTaskId())).thenReturn(Optional.ofNullable(firstTask));
//        when(TaskDto.fromTask(firstTask)).thenReturn(taskDto);
//
//        taskService.getTask(1L);
//
//        verify(taskRepository, times(1)).findById(any());
//        assertThrows(RecordNotFoundException.class, () ->
//                taskRepository.findById(80L));
//    }

    @Test
    void getTasks() {
        when(userRepository.findById("userName")).thenReturn(Optional.ofNullable(user));
        when(authenticationService.getCurrentUser()).thenReturn(authentication);
        when(userService.getCurrentUser()).thenReturn(user);
        when(taskRepository.findAllByTaskOwner(user, user)).thenReturn(firstTaskList);

        List<Task> foundTasks = taskService.getTasksForTaskOwner("userName");

        verify(taskRepository, times(1)).findAllByTaskOwner(user, user);
        verify(authenticationService, times(1)).getCurrentUser();
        verify(userRepository, times(1)).findById(any());

        assertEquals(foundTasks, firstTaskList);
    }

    @Test
    void getTasksForParentProject() {
        when(authenticationService.getCurrentUser()).thenReturn(authentication);
        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));
        when(userService.getCurrentUser()).thenReturn(user);
        when(taskRepository.findAllByParentProject(project, user)).thenReturn(firstTaskList);

        List<Task> foundTasks = taskService.getTasksForParentProject(1L);

        verify(authenticationService, times(1)).getCurrentUser();
        verify(taskRepository, times(1)).findAllByParentProject(project, user);
        verify(userService, times(1)).getCurrentUser();

        assertEquals(foundTasks, firstTaskList);

        assertThrows(RecordNotFoundException.class, () ->
                taskService.getTasksForParentProject(10L));
    }

    @Test
    void getTasksForTaskOwner() {
        when(authenticationService.getCurrentUser()).thenReturn(authentication);
        when(userRepository.findById("userName")).thenReturn(Optional.ofNullable(user));
        when(userService.getCurrentUser()).thenReturn(user);
        when(taskRepository.findAllByTaskOwner(user, user)).thenReturn(firstTaskList);

        List<Task> foundTasks = taskService.getTasksForTaskOwner("userName");

        verify(authenticationService, times(1)).getCurrentUser();
        verify(taskRepository, times(1)).findAllByTaskOwner(user, user);
        verify(userService, times(1)).getCurrentUser();

        assertEquals(foundTasks, firstTaskList);

        assertThrows(RecordNotFoundException.class, () ->
                taskService.getTasksForTaskOwner("noUserName"));
    }

    @Test
    void getTasksForParentTask() {
        when(authenticationService.getCurrentUser()).thenReturn(authentication);
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(firstTask));
        when(userService.getCurrentUser()).thenReturn(user);
        when(taskRepository.findAllByParentTask(firstTask, user)).thenReturn(firstTaskList);

        List<Task> foundTasks = taskService.getTasksForParentTask(1L);

        verify(taskRepository, times(1)).findAllByParentTask(firstTask, user);
        verify(taskRepository, times(1)).findById(any());
        verify(authenticationService, times(1)).getCurrentUser();
        verify(userService, times(1)).getCurrentUser();

        assertEquals(foundTasks, firstTaskList);

        assertThrows(RecordNotFoundException.class, () ->
                taskService.getTasksForParentTask(20L));
    }

    //////////////////////////////
    //// Update

    @Test
    void updateTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(firstTask));

        taskService.updateTask(1L, firstTask);

        verify(taskRepository, times(1)).findById(1L);
        assertThrows(RecordNotFoundException.class, () ->
                taskService.updateTask(3L, firstTask));
    }

    //////////////////////////////
    //// Delete

    @Test
    void deleteTask() {
        when(taskRepository.findById(1L)).thenReturn(Optional.ofNullable(firstTask));

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).findById(1L);
        verify(taskRepository, times(1)).deleteById(1L);
    }
}
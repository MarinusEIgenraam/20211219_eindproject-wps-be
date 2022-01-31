package com.willpowered.eindprojectwpsbe.Task;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    TaskRepository taskRepository;

    @Mock
    ProjectRepository projectRepository;

    @InjectMocks
    private TaskService taskService;

    @InjectMocks
    private UserAuthenticateService userAuthenticateService;

    @Captor
    ArgumentCaptor<Project> projectCaptor;

    private Project firstProject;
    private Project secondProject;
    private Task firstTask;
    private Task secondTask;
    private Task thirdTask;
    private Task firstSubtask;
    private Task secondSubtask;
    private Task thirdSubtask;
    private User firstUser;
    private User secondUser;

    @BeforeEach
    void setUp() {
        this.firstTask = new Task();
        firstTask.setTaskId(1L);
        firstTask.setTaskName("firstTask");
        firstTask.setDescription("firstDescription");
        secondTask.setTaskId(1L);
        secondTask.setTaskName("secondTask");
        secondTask.setDescription("secondDescription");
        List<Task> firstTaskList = Stream.of(firstTask, secondTask).collect(Collectors.toList());
        List<Task> secondTaskList = Stream.of(secondTask, firstTask).collect(Collectors.toList());
        this.firstProject = new Project();
        firstProject.setProjectId(1L);
        firstProject.setProjectName("firstProject");
        firstProject.setProjectTaskList(firstTaskList);
        this.secondProject = new Project();
        secondProject.setProjectId(2L);
        secondProject.setProjectName("secondProject");
        secondProject.setProjectTaskList(secondTaskList);


    }

    @Test
    void saveTask() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void saveTaskData() {
    }

    @Test
    void getTask() {
    }

    @Test
    void getTasks() {
    }

    @Test
    void getTasksForParentProject() {
    }

    @Test
    void getTasksForTaskOwner() {
    }

    @Test
    void getTasksForParentTask() {
    }

    @Test
    void deleteTask() {
    }
}
package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Category.Category;
import com.willpowered.eindprojectwpsbe.Category.CategoryRepository;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Task.Task;
import com.willpowered.eindprojectwpsbe.Task.TaskInputDto;
import com.willpowered.eindprojectwpsbe.Task.TaskRepository;
import com.willpowered.eindprojectwpsbe.Task.TaskService;
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
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {


    @InjectMocks
    ProjectService projectService;
    @Captor
    ArgumentCaptor<Project> projectCaptor;
    @Captor
    ArgumentCaptor<Task> taskCaptor;


    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    TaskRepository taskRepository;

    @Mock
    AuthenticationService authenticationService;
    @Mock
    UserService userService;
    @Mock
    TaskService taskService;

    @Mock
    Task task;
    @Mock
    Category category;
    @Mock
    Authentication authentication;
    @Mock
    User user;
    @Mock
    ProjectInputDto projectInputDto;
    @Mock
    TaskInputDto taskInputDto;
    @Mock
    ArrayList<Task> taskList;
    @Mock
    Pageable pageable;

    Project project;
    ProjectInputDto projectInput;
    List<Project> projectList;

    @BeforeEach
    void setUp() {
        project = Project.builder()
                .projectId(1L)
                .projectOwner(user)
                .category(category)
                .voteCount(2)
                .imageUrl("www.url.nl")
                .url("www.lru.nl")
                .projectTaskList(taskList)
                .projectName("Cool project")
                .editedTime(LocalDate.now())
                .build();
        List<String> collaborators = List.of("MockUser", "SecondUser");
        List<TaskInputDto> taskInputDtoList = List.of(taskInputDto);
        List<Project> projectList = List.of(project);

        projectInputDto.collaborators = collaborators;
        projectInputDto.projectTaskList = taskInputDtoList;
        projectInputDto.categoryId = 1L;


    }

    //////////////////////////////
    //// Create

    @Test
    void saveProject() {
        when(categoryRepository.findById(any())).thenReturn(Optional.ofNullable(category));
        when(userService.getCurrentUser()).thenReturn(user);
        when(userRepository.findById(any())).thenReturn(Optional.of(user));
        when(projectInputDto.toProject()).thenReturn(project);
        when(projectRepository.save(any())).thenReturn(project);
        when(taskService.saveTask(taskInputDto)).thenReturn(task);


        Project newProject = projectService.saveProject(projectInputDto);


        assertThat(project).isEqualTo(newProject);

        verify(projectRepository, times(2)).save(projectCaptor.capture());
        List<Project> capturedValues = projectCaptor.getAllValues();

        assertThat(capturedValues.size()).isEqualTo(2);
        assertThat(capturedValues.get(1).getCollaborators().contains(user)).isEqualTo(true);
    }

    //////////////////////////////
    //// Read

    @Test
    void getAllProjects() {

    }

    @Test
    void getProject() {
        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));

        Project foundProject = projectService.getProject(1L);
        verify(projectRepository, times(1)).findById(1L);

        assertThat(foundProject.getProjectName()).isEqualTo(project.getProjectName());
    }

    @Test
    void getProjectsForCategoryWithCurrentUser() {
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));
        when(authenticationService.getCurrentUser()).thenReturn(authentication);
        when(userService.getCurrentUser()).thenReturn(user);
        when(projectRepository.findAllByCategory(1L, user, pageable)).thenReturn(projectList);

        List<Project> foundList = projectService.getProjectsForCategory(1L, pageable);

        verify(projectRepository, times(1)).findAllByCategory(1L, user, pageable);
        assertThat(foundList).isEqualTo(projectList);
    }

    @Test
    void getProjectsForCategoryWithNoUser() {
        when(authenticationService.getCurrentUser()).thenReturn(null);
        when(categoryRepository.findById(1L)).thenReturn(Optional.ofNullable(category));
        when(projectRepository.findAllByCategory(1L, null, pageable)).thenReturn(projectList);

        List<Project> foundList = projectService.getProjectsForCategory(1L, pageable);

        verify(projectRepository, times(1)).findAllByCategory(1L, null, pageable);
        assertThat(foundList).isEqualTo(projectList);
    }

    @Test
    public void getProjectsForCategoryExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> projectService.getProjectsForCategory(null, pageable));
    }

    @Test
    void getProjectsForProjectOwner() {
        when(userRepository.findById(user.getUsername())).thenReturn(Optional.ofNullable(user));
        when(projectRepository.findAllByProjectOwner(user, pageable)).thenReturn(projectList);

        List<Project> foundList = projectService.getProjectsForProjectOwner(user.getUsername(), pageable);

        verify(projectRepository, times(1)).findAllByProjectOwner(any(), any());
        assertEquals(foundList, projectList);
    }

    @Test
    public void getProjectForProjectOwnerExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> projectService.getProjectsForProjectOwner(null, pageable));
    }

    @Test
    void getProjectsForProjectCollaborator() {
        when(userRepository.findById(user.getUsername())).thenReturn(Optional.ofNullable(user));
        when(authenticationService.getCurrentUser()).thenReturn(authentication);
        when(userService.getCurrentUser()).thenReturn(user);
        when(projectRepository.findAllByCollaborators(user, user, pageable)).thenReturn(projectList);

        List<Project> foundList = projectService.getProjectsForProjectCollaborator(user.getUsername(), pageable);

        verify(projectRepository, times(1)).findAllByCollaborators(any(), any(), any());
        assertEquals(foundList, projectList);
    }

    @Test
    public void getProjectForProjectCollaboratorsExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> projectService.getProjectsForProjectCollaborator(null, pageable));
    }


    //////////////////////////////
    //// Update

    @Test
    void updateProjectWithSameTaskList() {
        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));
        when(projectRepository.save(project)).thenReturn(project);

        projectService.updateProject(1L, project);

        verify(projectRepository, times(1)).save(projectCaptor.capture());
        var capturedProject = projectCaptor.getValue();
        assertEquals(project, capturedProject);
    }

    @Test
    void updateProjectWithNewTaskList() {
        List<Task> newTaskList = List.of(task, task, task);
        Project newProject = Project.builder()
                .projectId(1L)
                .projectTaskList(newTaskList)
                .build();
        newProject.setProjectTaskList(newTaskList);

        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));
        when(projectRepository.save(newProject)).thenReturn(newProject);
        when(taskRepository.findById(any())).thenReturn(Optional.ofNullable(task));
        when(taskRepository.save(task)).thenReturn(task);


        projectService.updateProject(1L, newProject);


        verify(projectRepository, times(2)).save(projectCaptor.capture());
        var capturedProject = projectCaptor.getAllValues();
        assertThat(project.getProjectTaskList()).isNotEqualTo(capturedProject.get(1).getProjectTaskList());
    }

    //////////////////////////////
    //// Delete

    @Test
    void deleteProject() {
        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(project));

        projectService.deleteProject(1L);

        verify(projectRepository, times(1)).findById(1L);
        verify(projectRepository, times(1)).deleteById(1L);
    }
}


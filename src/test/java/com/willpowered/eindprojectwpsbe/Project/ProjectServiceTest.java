package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Category.Category;
import com.willpowered.eindprojectwpsbe.Category.CategoryRepository;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Task.Task;
import com.willpowered.eindprojectwpsbe.Task.TaskRepository;
import com.willpowered.eindprojectwpsbe.Authentication.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Authentication.User;
import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Authentication.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {


    @InjectMocks
    private ProjectService projectService;
    @InjectMocks
    private AuthenticationService authenticationService;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ProjectRepository projectRepository;
    @Mock
    TaskRepository taskRepository;
    @Mock
    UserRepository userRepository;

    private Project firstProject;
    private Project secondProject;
    private Project thirdProject;
    private User firstUser;
    private User secondUser;
    private User thirdUser;
    private Category firstCategory;
    private Task firstTask;
    private Portal thirdPortal;
    private Pageable pageable;
    private List<Blog> firstBlogList;
    private Page<Blog> page;

    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();
        this.firstUser = new User("firstUser", "password", true, "user@user.nl", authorities);
        this.secondUser = new User("secondUser", "password", true, "user@user.nl", authorities);
        this.thirdUser = new User("thirdUser", "password", true, "user@user.nl", authorities);
        this.firstProject = new Project();
//        this.firstCategory = new Category(1L, "content", "sdafdsf", List<Project> newList = new List<Project>());
        this.secondProject =new Project();
        this.thirdProject =new Project();
        }

        @Test
        void getAllProjects () {
        }

        @Test
        void saveProject () {
            ProjectDto projectDto = new ProjectDto().fromProject(firstProject);
            when(categoryRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(firstCategory));
            when(userRepository.findById(firstUser.getUsername())).thenReturn(java.util.Optional.ofNullable(firstUser));
            ProjectInputDto projectInputDto = new ProjectInputDto();
            projectInputDto.projectName = "Hk;jsdfhaf";

            Project newProject = projectService.saveProject(projectInputDto);
            verify(categoryRepository, times(1)).findById(1L);
            verify(userRepository, times(1)).findById(firstUser.getUsername());

            assertThat(newProject.getProjectName()).isEqualTo(projectInputDto.projectName);


        }

        @Test
        void updateProject () {
        }

        @Test
        void getProject () {
        }

        @Test
        void getProjectsForCategory () {
        }

        @Test
        void getProjectsForProjectOwner () {
        }

        @Test
        void getProjectsForProjectCollaborator () {
        }

        @Test
        void deleteProject () {
        }
    }

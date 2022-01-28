package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Comment.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;


class ProjectControllerTest {


        ProjectService projectService = Mockito.mock(ProjectService.class);


//        ProjectService projectService = new ProjectService();

//        private ProjectService projectService;


        ProjectController projectController = new ProjectController(projectService, Mockito.mock(CommentService.class));


//    @Test
//    void testSaveProject() {
//        ProjectInputDto projectInputDto = new ProjectInputDto();
//        Project project = new Project();
//        given(projectService.saveProject(projectInputDto)).willReturn(project);
//        ProjectDto projectDto = projectController.saveProject(projectInputDto);
//        assertEquals(projectDto.projectId, "ID1");
//    }

}
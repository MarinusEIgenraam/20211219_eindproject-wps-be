package com.willpowered.eindprojectwpsbe.Comment;

import com.willpowered.eindprojectwpsbe.Alert.Alert;
import com.willpowered.eindprojectwpsbe.Alert.AlertService;
import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Blog.BlogRepository;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.TestEntityService;
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
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BlogRepository blogRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    AlertService alertService;
    @Mock
    UserService userService;
    @InjectMocks
    CommentService commentService;

    @Captor
    ArgumentCaptor<Comment> commentCaptor;

    private User firstUser;
    private User secondUser;

    private Comment firstComment;
    private Comment secondComment;

    private Project firstProject;
    private Project seProject;
    private Blog firstBlog;
    private Alert firstAlert;
    private List<Alert> firstAlertList;

    @Mock
    Blog blog;
    @Mock
    Project project;
    @Mock
    Alert alert;


    @Mock
    TestEntityService s;


    @BeforeEach
    void setUp() {
        firstUser = User.builder()
                .username("firstUser")
                .password("firstPassword")
                .build();
        secondUser = User.builder()
                .username("secondUser")
                .password("secondPassword")
                .build();

        firstComment = Comment.builder()
                .build();

        firstBlog = Blog.builder()
                .blogName("firstBlog")
                .blogOwner(secondUser)
                .blogId(1L)
                .build();
        firstAlert = Alert.builder()
                .id(1L)
                .title("Task invitation")
                .text("")
                .createdAt(LocalDate.parse("2022-02-08"))
                .build();


    }

    //////////////////////////////
    //// Create

    @Test
    void saveComment() {
//        CommentInputDto dtoPC = new CommentInputDto();
//        CommentInputDto commentInputDto = new CommentInputDto();
//        Comment commentPC = s.makeComment("Comment");
//        Comment commentPP = s.makeComment("Project");
//        Comment commentPB = s.makeComment("Blog");
//        commentPC.setParentComment(s.makeComment("ParentComment"));
//        commentPP.setParentProject(s.makeProject("ParentProject"));
//        commentPB.setParentBlog(s.makeBlog("ParentBlog"));
//
//        CommentInputDto dtoPP = new CommentInputDto();
//        CommentInputDto dtoPB = new CommentInputDto();
//
//        dtoPC.parentCommentId = 1L;
//        dtoPP.parentProjectId = 1L;
//        dtoPB.parentBlogId = 1L;
//        dtoPB.text = "sdf";
//
//        when(commentInputDto.toComment()).thenReturn(commentPC, commentPB, commentPP);
//        when(commentRepository.save(commentPC)).
//
//
//        Comment resultCommentPC = commentService.saveComment(dtoPC);
//        Comment resultCommentPP = commentService.saveComment(dtoPP);
//        Comment resultCommentPB = commentService.saveComment(dtoPB);
//
//        verify(commentRepository, times(3)).save(any());
//        assertThat(resultCommentPC.getParentComment().getId()).isEqualTo(1L);

    }

    @Test
    void setParentBlog() {
//        CommentInputDto dto = new CommentInputDto();
//        dto.parentBlogId = 1L;
//        when(blogRepository.findById(1L)).thenReturn(Optional.ofNullable(blog));
//
//        commentService.setParentBlog(dto, s.makeComment("newComment"));
//
//        verify(blogRepository, times(1)).findById(any());
    }

    @Test
    void testSaveComment() {
    }

    @Test
    void setParentProject() {
    }

    @Test
    void saveNewComment() {
    }

    //////////////////////////////
    //// Read

    @Test
    void getComment() {
    }

    @Test
    void getCommentsForParentProject() {
    }

    @Test
    void getCommentsForParentBlog() {
    }

    @Test
    void getCommentsForParentComment() {
    }

    @Test
    void getCommentsForUser() {
    }

    @Test
    void updateComment() {
    }

    @Test
    void deleteComment() {
    }

    @Test
    void calculateComments() {
    }


    @Test
    void testGetComment() {
    }

    @Test
    void testGetCommentsForParentProject() {
    }

    @Test
    void testGetCommentsForParentBlog() {
    }

    @Test
    void testGetCommentsForParentComment() {
    }

    @Test
    void testGetCommentsForUser() {
    }

    @Test
    void testUpdateComment() {
    }

    @Test
    void testDeleteComment() {
    }

    @Test
    void testCalculateComments() {
    }
}
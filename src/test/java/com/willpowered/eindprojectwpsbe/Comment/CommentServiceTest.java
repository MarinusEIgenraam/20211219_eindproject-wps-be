package com.willpowered.eindprojectwpsbe.Comment;

import com.willpowered.eindprojectwpsbe.Alert.Alert;
import com.willpowered.eindprojectwpsbe.Alert.AlertService;
import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Blog.BlogRepository;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.TestEntityService;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    CommentService commentService;
    @Captor
    ArgumentCaptor<Comment> commentCaptor;

    @Mock
    ProjectRepository projectRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    BlogRepository blogRepository;
    @Mock
    CommentRepository commentRepository;
    @Mock
    UserService userService;
    @Mock
    AlertService alertService;

    @Mock
    User user;


    @Mock
    Blog parentBlog;
    @Mock
    Project parentProject;
    @Mock
    Comment parentComment;


    @Mock
    List<Comment> commentList;
    @Mock
    CommentInputDto commentInputDto;
    @Mock
    Pageable pageable;

    private Comment firstComment;


    @BeforeEach
    void setUp() {
        firstComment = Comment.builder()
                .id(1L)
                .text("This is some comment text")
                .user(user)
                .commentList(commentList)
                .startTime(LocalDateTime.now())
                .build();


    }

    //////////////////////////////
    //// Create

    @Test
    void saveComment() {
        firstComment.setUser(user);
        when(userService.getCurrentUser()).thenReturn(user);

        // Parent project
        CommentInputDto dtoParentProject = commentInputDto;
        dtoParentProject.parentProjectId = 1L;
        Comment firstPPComment = firstComment;
        firstPPComment.setParentProject(parentProject);
        when(dtoParentProject.toComment()).thenReturn(firstPPComment);
        when(commentRepository.save(firstPPComment)).thenReturn(firstPPComment);

        Comment savedPProjectComment = commentService.saveComment(dtoParentProject);

        assertEquals(firstPPComment, savedPProjectComment);

        // Parent Blog
        CommentInputDto dtoParentBlog = commentInputDto;
        dtoParentBlog.parentBlogId = 1L;
        Comment firstPBComment = firstComment;
        firstPBComment.setParentBlog(parentBlog);
        when(dtoParentBlog.toComment()).thenReturn(firstPBComment);
        when(commentRepository.save(firstPBComment)).thenReturn(firstPBComment);

        Comment savedPBlogComment = commentService.saveComment(dtoParentBlog);

        assertEquals(firstPBComment, savedPBlogComment);

        // Parent comment
        CommentInputDto dtoParentComment = commentInputDto;
        dtoParentComment.parentCommentId = 1L;
        Comment firstPCComment = firstComment;
        firstPCComment.setParentComment(parentComment);
        when(dtoParentComment.toComment()).thenReturn(firstPCComment);
        when(commentRepository.save(firstPCComment)).thenReturn(firstPCComment);

        Comment savedPCommentComment = commentService.saveComment(dtoParentComment);

        assertEquals(firstPCComment, savedPCommentComment);
    }

    @Test
    void setParentBlog() {
        parentBlog.setBlogId(2L);
        commentInputDto.parentBlogId = 2L;
        when(blogRepository.findById(2L)).thenReturn(Optional.ofNullable(parentBlog));

        assertThat(firstComment.getParentBlog()).isEqualTo(null);
        commentService.setParentBlog(commentInputDto, firstComment);
        assertThat(firstComment.getParentBlog()).isEqualTo(parentBlog);
    }

    @Test
    void setParentProject() {
        parentProject.setProjectId(2L);
        commentInputDto.parentProjectId = 2L;
        when(projectRepository.findById(2L)).thenReturn(Optional.ofNullable(parentProject));

        assertThat(firstComment.getParentProject()).isEqualTo(null);
        commentService.setParentProject(commentInputDto, firstComment);
        assertThat(firstComment.getParentProject()).isEqualTo(parentProject);
    }

    @Test
    void saveNewComment() {
        commentInputDto.parentCommentId = 2L;
        parentComment.setId(2L);
        parentComment.setUser(user);
        firstComment.setParentComment(parentComment);


        when(commentRepository.findById(2L)).thenReturn(Optional.ofNullable(parentComment));
        when(commentRepository.save(firstComment)).thenReturn(firstComment);


        Comment savedComment = commentService.saveNewComment(commentInputDto, firstComment);


        verify(commentRepository, times(1)).findById(2L);
        verify(commentRepository, times(1)).save(firstComment);
        verify(commentRepository, times(1)).save(parentComment);
        assertThat(savedComment.getParentComment()).isEqualTo(parentComment);
    }

    //////////////////////////////
    //// Read

    @Test
    void getComment() {
        when(commentRepository.findById(1L)).thenReturn(Optional.ofNullable(firstComment));

        assertThat(commentService.getComment(1L)).isEqualTo(firstComment);
    }

    @Test
    void getCommentsForParentProject() {
        when(projectRepository.findById(1L)).thenReturn(Optional.ofNullable(parentProject));
        when(commentRepository.findAllByParentProject(parentProject, pageable)).thenReturn(commentList);

        List<Comment> newCommentList = commentService.getCommentsForParentProject(1L, pageable);

        verify(commentRepository, times(1)).findAllByParentProject(parentProject, pageable);
        verify(projectRepository, times(1)).findById(1L);
        assertEquals(commentList, newCommentList);
    }

    @Test
    void getCommentsForParentBlog() {
        when(blogRepository.findById(1L)).thenReturn(Optional.ofNullable(parentBlog));
        when(commentRepository.findAllByParentBlog(parentBlog, pageable)).thenReturn(commentList);

        List<Comment> newCommentList = commentService.getCommentsForParentBlog(1L, pageable);

        verify(commentRepository, times(1)).findAllByParentBlog(parentBlog, pageable);
        verify(blogRepository, times(1)).findById(1L);
        assertEquals(commentList, newCommentList);
    }

    @Test
    void getCommentsForParentComment() {
        when(commentRepository.findById(1L)).thenReturn(Optional.ofNullable(parentComment));
        when(commentRepository.findAllByParentComment(parentComment, pageable)).thenReturn(commentList);

        List<Comment> newCommentList = commentService.getCommentsForParentComment(1L, pageable);

        verify(commentRepository, times(1)).findAllByParentComment(parentComment, pageable);
        verify(commentRepository, times(1)).findById(1L);
        assertEquals(commentList, newCommentList);
    }

    @Test
    void getCommentsForUser() {
        when(userRepository.findById(user.getUsername())).thenReturn(Optional.ofNullable(user));
        when(commentRepository.findAllByUser(user, pageable)).thenReturn(commentList);

        List<Comment> newCommentList = commentService.getCommentsForUser(user.getUsername(), pageable);

        verify(commentRepository, times(1)).findAllByUser(user, pageable);
        verify(userRepository, times(1)).findById(user.getUsername());
        assertEquals(commentList, newCommentList);
    }

    //////////////////////////////
    //// Update

    @Test
    void updateComment() {
        Comment newComment = firstComment;
        newComment.setText("This is some comment text");
        when(commentRepository.findById(1L)).thenReturn(Optional.ofNullable(firstComment));
        assertThat(firstComment.getText()).isEqualTo("This is some comment text");

        commentService.updateComment(1L, newComment);

        verify(commentRepository, times(1)).save(commentCaptor.capture());
        var capturedComment = commentCaptor.getValue();

        assertThat(newComment).isEqualTo(capturedComment);
    }

    @Test
    void deleteComment() {
        when(commentRepository.findById(1L)).thenReturn(Optional.ofNullable(firstComment));

        commentService.deleteComment(1L);

        verify(commentRepository, times(1)).findById(1L);
        verify(commentRepository, times(1)).deleteById(1L);
    }

    //////////////////////////////
    //// Helpers

    @Test
    void calculateComments() {
        when(commentRepository.findAllByParentProject(parentProject)).thenReturn(commentList);
        when(commentList.size()).thenReturn(100);

        Integer listSize = commentService.calculateComments(parentProject);

        verify(commentRepository, times(1)).findAllByParentProject(parentProject);
        assertEquals(100, listSize);
    }

    //////////////////////////////
    //// Exceptions

    @Test
    void findByIdException() {
        assertThrows(RecordNotFoundException.class, () -> commentService.getComment(null));
    }

}
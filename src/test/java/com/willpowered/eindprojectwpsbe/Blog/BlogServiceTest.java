package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.Instant;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @InjectMocks
    private BlogService blogService;
    @Mock
    private UserService userService;
    @Mock
    BlogRepository blogRepository;
    @Mock
    UserRepository userRepository;
    @Mock
    Blog blog;
    @Mock
    User user;
    @Mock
    Pageable pageable;
    @Mock
    Page<Blog> blogPage;

    @Captor
    ArgumentCaptor<Blog> blogCaptor;


//    @BeforeEach
//    void setUp() {
//        Set<Authority> authorities = new HashSet<>();
//        targetUser = User.builder()
//                .username("targetUser")
//                .password("password")
//                .email("email@targetuser.nl")
//                .build();
//        firstBlog = Blog.builder()
//                .blogId(1L)
//                .blogName("Best blog ever")
//                .url("www.thebest.nl")
//                .imageUrl("www.prettyimage.nl")
//                .description("This is the best of the best")
//                .build();
//        Blog secondBlog = Blog.builder()
//                .blogId(2L)
//                .blogName("Second best blog ever")
//                .url("www.thesecondbest.nl")
//                .imageUrl("www.mediocreimage.nl")
//                .description("This is the second best of the best")
//                .build();
//        Blog thirdBlog = Blog.builder()
//                .blogId(3L)
//                .blogName("Third best blog ever")
//                .url("www.thethirdbest.nl")
//                .imageUrl("www.uglyimage.nl")
//                .description("This is the third best of the best")
//                .build();
//        firstBlogList = Arrays.asList(firstBlog, secondBlog, thirdBlog);
//
//    }


    //////////////////////////////
    //// Create

    @Test
    void saveBlog() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(blogRepository.save(blog)).thenReturn(blog);

        blogService.saveBlog(blog);

        verify(blogRepository, times(1)).save(blogCaptor.capture());
        Blog capturedBlog = blogCaptor.getValue();

        assertThat(blog).isEqualTo(capturedBlog);
    }

    //////////////////////////////
    //// Read

    @Test
    void getBlog() {
        when(blogRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(blog));

        Blog foundBlog = blogService.getBlog(1L);
        verify(blogRepository, times(1)).findById(1L);

        assertThat(blog.getBlogOwner()).isEqualTo(foundBlog.getBlogOwner());
    }

    @Test
    void getBlogsForBlogOwner() {
        when(blogRepository.findAllByBlogOwner(user, pageable)).thenReturn(blogPage);
        when(userRepository.findById(user.getUsername())).thenReturn(java.util.Optional.ofNullable(user));

        Page<Blog> actualResponse = blogService.getBlogsForBlogOwner(user.getUsername(), pageable);

        verify(blogRepository, times(1)).findAllByBlogOwner(user, pageable);
        assertThat(blogPage).isEqualTo(actualResponse);
    }


    //////////////////////////////
    //// Update

    @Test
    void updateBlog() {
        when(blogRepository.findById(blog.getBlogId())).thenReturn(java.util.Optional.ofNullable(blog));
        when(blogRepository.save(blog)).thenReturn(blog);

        blogService.updateBlog(1L, blog);

        verify(blogRepository, times(1)).findById(blog.getBlogId());
        verify(blogRepository, times(1)).save(blogCaptor.capture());
        var capturedBlog = blogCaptor.getValue();

        assertThat(capturedBlog.getBlogId()).isEqualTo(blog.getBlogId());
    }

    //////////////////////////////
    //// Delete

    @Test
    void deleteBlog() {
        when(blogRepository.findById(1L)).thenReturn(Optional.ofNullable(blog));
        blogService.deleteBlog(1L);

        verify(blogRepository, times(1)).findById(1L);
        verify(blogRepository, times(1)).deleteById(1L);
    }

    @Test
    void getDeleteException() {
        assertThrows(RecordNotFoundException.class, () -> blogService.deleteBlog(null));
    }
}
package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @InjectMocks
    private BlogService blogService;
    @Mock
    private AuthenticationService authenticationService;
    @Mock
    BlogRepository blogRepository;
    @Mock
    UserRepository userRepository;

    @Captor
    ArgumentCaptor<Blog> blogCaptor;

    private User targetUser;
    private User currentUser;
    
    private Blog firstBlog;
    private Blog secondBlog;
    private Blog thirdBlog;
    
    private Portal firstPortal;
    private Portal secondPortal;
    private Portal thirdPortal;
    
    private Pageable pageable;
    private Page<Blog> page;

    private List<Blog> firstBlogList = Arrays.asList(firstBlog, secondBlog, thirdBlog);

    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();
        targetUser = User.builder()
                .username("targetUser")
                .password("password")
                .email("email@targetuser.nl")
                .build();
        currentUser = User.builder()
                .username("currentUser")
                .password("password")
                .email("email@currentuser.nl")
                .build();
        firstBlog = Blog.builder()
                .blogId(1L)
                .blogName("Best blog ever")
                .url("www.thebest.nl")
                .imageUrl("www.prettyimage.nl")
                .description("This is the best of the best")
                .build();
        secondBlog = Blog.builder()
                .blogId(2L)
                .blogName("Second best blog ever")
                .url("www.thesecondbest.nl")
                .imageUrl("www.mediocreimage.nl")
                .description("This is the second best of the best")
                .build();
        thirdBlog = Blog.builder()
                .blogId(3L)
                .blogName("Third best blog ever")
                .url("www.thethirdbest.nl")
                .imageUrl("www.uglyimage.nl")
                .description("This is the third best of the best")
                .build();

    }


    //////////////////////////////
    //// Create

    @Test
    void saveBlog() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.setContext(securityContext);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();

    }

    //////////////////////////////
    //// Read

    @Test
    void getBlog() {

        when(blogRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(firstBlog));

        Blog blog = blogService.getBlog(1L);
        verify(blogRepository, times(1)).findById(1L);

        assertThat(blog.getBlogOwner()).isEqualTo(firstBlog.getBlogOwner());
    }

    @Test
    void getBlogsForBlogOwner() {
        PageImpl pagedResponse = new PageImpl(firstBlogList);
        when(blogRepository.findAllByBlogOwner(targetUser, pageable)).thenReturn(pagedResponse);
        when(userRepository.findById(targetUser.getUsername())).thenReturn(java.util.Optional.ofNullable(targetUser));

        Page actualResponse = blogService.getBlogsForBlogOwner(targetUser.getUsername(), pageable);

        verify(blogRepository, times(1)).findAllByBlogOwner(targetUser, pageable);
        assertThat(pagedResponse).isEqualTo(actualResponse);
    }


    //////////////////////////////
    //// Update

    @Test
    void updateBlog() {
        when(blogRepository.findById(firstBlog.getBlogId())).thenReturn(java.util.Optional.ofNullable(firstBlog));
        when(blogRepository.save(firstBlog)).thenReturn(firstBlog);

        blogService.updateBlog(1L, firstBlog);

        verify(blogRepository, times(1)).findById(firstBlog.getBlogId());
        verify(blogRepository, times(1)).save(blogCaptor.capture());
        var capturedBlog = blogCaptor.getValue();

        assertThat(capturedBlog.getBlogId()).isEqualTo(firstBlog.getBlogId());
    }

    //////////////////////////////
    //// Delete

    @Test
    void deleteBlog() {
        blogRepository.delete(firstBlog);
        blogRepository.deleteById(1L);

        verify(blogRepository, times(1)).delete(firstBlog);
    }


}
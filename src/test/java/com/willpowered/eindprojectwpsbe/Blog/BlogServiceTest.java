package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.auth.Authority;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithUserDetails;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BlogServiceTest {

    @InjectMocks
    private BlogService blogService;
    @InjectMocks
    private UserAuthenticateService userAuthenticateService;
    @Mock
    BlogRepository blogRepository;
    @Mock
    UserRepository userRepository;

    private Blog firstBlog;
    private Blog secondBlog;
    private Blog thirdBlog;
    private User firstUser;
    private User secondUser;
    private User thirdUser;
    private Portal firstPortal;
    private Portal secondPortal;
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
        this.firstBlog = new Blog(1L, "Bla", "Bla", "bla", "bla", Instant.now(), firstUser);
        this.secondBlog = new Blog(1L, "Bla", "Bla", "bla", "bla", Instant.now(), firstUser);
        this.thirdBlog = new Blog(1L, "Bla", "Bla", "bla", "bla", Instant.now(), firstUser);

    }

    @Test
    void getBlogsForBlogOwner() {
        List<Blog> blogs = new ArrayList<>();
        blogs.add(firstBlog);
        blogs.add(secondBlog);
        blogs.add(thirdBlog);
        PageImpl pagedResponse = new PageImpl(blogs);

        when(blogRepository.findAllByBlogOwner(firstUser, pageable)).thenReturn(pagedResponse);
        when(userRepository.findById(firstUser.getUsername())).thenReturn(java.util.Optional.ofNullable(firstUser));
        Page actualResponse = blogService.getBlogsForBlogOwner(firstUser.getUsername(), pageable);
        verify(blogRepository, times(1)).findAllByBlogOwner(firstUser, pageable);

        assertThat(pagedResponse).isEqualTo(actualResponse);
    }

    @Test
    void getBlog() {

        when(blogRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(firstBlog));

        Blog blog = blogService.getBlog(1L);
        verify(blogRepository, times(1)).findById(1L);

        assertThat(blog.getBlogOwner()).isEqualTo(firstBlog.getBlogOwner());
    }

    @Test
    @WithUserDetails("customUsername")
    void saveBlog() {
        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
        Authentication authentication = Mockito.mock(Authentication.class);
        SecurityContextHolder.setContext(securityContext);
        Authentication a = SecurityContextHolder.getContext().getAuthentication();

//
//        Mockito.when(securityContext.getAuthentication()).thenReturn(authentication);
//
//        when(userAuthenticateService.getCurrentUser()).thenReturn(firstUser);
//        when(blogRepository.save(firstBlog)).thenReturn(firstBlog);
//
//        when(userRepository.findByUsername(firstUser.getUsername())).thenReturn(java.util.Optional.ofNullable(firstUser));
//        Blog newBlog = blogService.saveBlog(firstBlog);
//        verify(userAuthenticateService, times(1)).getCurrentUser();
//        verify(blogRepository, times(1)).save(firstBlog);
//
//        assertThat(newBlog.getBlogId()).isEqualTo(firstBlog.getBlogId());


    }

    @Test
    void updateBlog() {

        when(blogRepository.findById(firstBlog.getBlogId())).thenReturn(java.util.Optional.ofNullable(firstBlog));
        when(blogRepository.save(firstBlog)).thenReturn(firstBlog);
        blogService.updateBlog(1L, firstBlog);
        verify(blogRepository, times(1)).findById(firstBlog.getBlogId());
        verify(blogRepository, times(1)).save(firstBlog);


    }

    @Test
    void deleteBlog() {
        Blog blog = new Blog();
        blog.setBlogName("Testi");

        blogRepository.delete(blog);

        blogRepository.deleteById(1L);

        verify(blogRepository, times(1)).delete(blog);
    }
}
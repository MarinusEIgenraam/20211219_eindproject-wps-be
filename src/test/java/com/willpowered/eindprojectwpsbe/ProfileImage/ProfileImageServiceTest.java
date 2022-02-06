package com.willpowered.eindprojectwpsbe.ProfileImage;

import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Blog.BlogRepository;
import com.willpowered.eindprojectwpsbe.Blog.BlogService;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalService;
import com.willpowered.eindprojectwpsbe.auth.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import java.time.Instant;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileImageServiceTest {

    @InjectMocks
    private ProfileImageService profileImageService;
    @InjectMocks
    private PortalService portalService;
    @InjectMocks
    private UserService userService;
    @InjectMocks
    private UserAuthenticateService userAuthenticateService;
    @Mock
    ProfileImageRepository profileImageRepository;
    @Mock
    UserRepository userRepository;

    private ProfileImage firstProfileImage;
    private ProfileImage secondProfileImage;
    private ProfileImage thirdProfileImage;
    private User firstUser;
    private User secondUser;
    private User thirdUser;
    private Portal firstPortal;
    private Portal secondPortal;
    private Portal thirdPortal;
    private Pageable pageable;
    private List<ProfileImage> firstProfileImageList;
    private Page<ProfileImage> page;

    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();
        this.firstUser = new User("firstUser", "password", true, "user@user.nl", authorities);
        this.secondUser = new User("secondUser", "password", true, "user@user.nl", authorities);
        this.thirdUser = new User("thirdUser", "password", true, "user@user.nl", authorities);
        this.firstProfileImage = new ProfileImage();
        firstProfileImage.setTitle("first");
        this.secondProfileImage = new ProfileImage();
        this.firstPortal = new Portal();
        secondProfileImage.setTitle("second");
        this.thirdProfileImage = new ProfileImage();
        thirdProfileImage.setTitle("third");
        ProfileImage[] newList = new ProfileImage[]{firstProfileImage, secondProfileImage, thirdProfileImage};
        firstProfileImageList = Arrays.asList(newList);


    }

    @Test
    void getFiles() {

        when(profileImageRepository.findAll()).thenReturn(firstProfileImageList);
        Iterable<ProfileImage> madeList = profileImageService.getFiles();

        verify(profileImageRepository, times(1)).findAll();

        assertThat(madeList.toString()).isEqualTo(firstProfileImageList.toString());


    }
//
//    @Test
//    void uploadFile() {
//        MultipartFile multiPartFile = mock(MultipartFile.class);
//        Authentication authentication = mock(Authentication.class);
//// Mockito.whens() for your authorization object
//        SecurityContext securityContext = mock(SecurityContext.class);
//        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) securityContext.getAuthentication().getPrincipal();
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//        when(userAuthenticateService.getCurrentUser()).thenReturn(firstUser);
//        when(userRepository.findByUsername(principal.getUsername())).thenReturn(java.util.Optional.ofNullable(secondUser));
//
//    }

    @Test
    void deleteFile() {

        profileImageRepository.delete(firstProfileImage);
        profileImageRepository.deleteById(1L);
        verify(profileImageRepository, times(1)).delete(firstProfileImage);

    }

//    @Test
//    void getFileByPortal() {
//        when(profileImageRepository.findByPortal(firstPortal)).thenReturn(java.util.Optional.ofNullable(firstProfileImage));
//        ProfileImageDto profileImageDto = profileImageService.getFileByPortal(firstPortal);
//        verify(profileImageRepository, times(1)).findByPortal(firstPortal);
//        ProfileImageDto newProfileImageDto = new ProfileImageDto().fromProfileImage(firstProfileImage);
//        assertThat(profileImageDto).isEqualTo(newProfileImageDto);
//    }

//    @Test
//    void downloadFile() {
//        when(profileImageRepository.findByPortal(portalService.getUserPortal(userService.getUser(firstUser.getUsername())))).thenReturn(java.util.Optional.ofNullable(firstProfileImage));
//
//    }

    @Test
    void getFileById() {
        when(profileImageRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(firstProfileImage));
        profileImageService.getFileById(1L);
        verify(profileImageRepository, times(1)).findById(1L);
    }

    @Test
    void fileExistsById() {
        when(profileImageRepository.existsById(1L)).thenReturn(true);
        boolean yes = profileImageService.fileExistsById(1L);
        assertTrue(yes);
    }
}
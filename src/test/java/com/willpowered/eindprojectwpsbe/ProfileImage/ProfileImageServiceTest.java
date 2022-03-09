package com.willpowered.eindprojectwpsbe.ProfileImage;

import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.Portal.PortalService;
import com.willpowered.eindprojectwpsbe.Authentication.*;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileImageServiceTest {

    @InjectMocks
    private ProfileImageService profileImageService;

    @Mock
    ProfileImageRepository profileImageRepository;
    @Mock
    PortalRepository portalRepository;
    @Mock
    private UserService userService;
    @Mock
    PortalService portalService;
    @Mock
    UserRepository userRepository;

    @Mock
    User user;
    @Mock
    Portal portal;
    @Mock
    Files files;
    @Mock
    MultipartFile multipartFile;
    @Mock
    InputStream inputStream;

    private InputStream is;
    private MockMvc mockMvc;

    private ProfileImage profileImage;
    private ProfileImage secondProfileImage;
    private Iterable<ProfileImage> firstProfileImageList;
    private Page<ProfileImage> page;


    @BeforeEach
    void setUp() {
        profileImage = ProfileImage.builder()
                .id(1L)
                .fileName("Pretty image")
                .location("uploads/prettyImage")
                .mediaType("jpg")
                .uploadedTimestamp(new Date(System.currentTimeMillis()))
                .uploadedBy("user")
                .build();


    }

    @Test
    void uploadFile() throws Exception {
        when(userService.getCurrentUser()).thenReturn(user);
        when(portalRepository.findByPortalOwner(user)).thenReturn(java.util.Optional.ofNullable(portal));
        when(profileImageRepository.save(any())).thenReturn(profileImage);
        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "excel.xlsx", "multipart/form-data", is);


        Long newId = profileImageService.uploadFile(mockMultipartFile);

        verify(userService, times(1)).getCurrentUser();
        verify(portalRepository, times(1)).findByPortalOwner(user);
        verify(profileImageRepository, times(1)).save(any());
        assertEquals(newId, profileImage.getId());
    }

    @Test
    void getFiles() {
        when(profileImageRepository.findAll()).thenReturn(firstProfileImageList);

        Iterable<ProfileImage> madeList = profileImageService.getFiles();

        verify(profileImageRepository, times(1)).findAll();
        assertThat(madeList).isEqualTo(firstProfileImageList);
    }

    @Test
    void deleteFile() {

        profileImageRepository.delete(profileImage);
        profileImageRepository.deleteById(1L);
        verify(profileImageRepository, times(1)).delete(profileImage);

    }

    @Test
    void getFileByPortal() {
}

    @Test
    void downloadFile() {
        final Path uploadDir = Paths.get("uploads/");
        when(userService.getUser(user.getUsername())).thenReturn(user);
        when(portalService.getUserPortal(user)).thenReturn(portal);
        when(profileImageRepository.findByPortal(portal)).thenReturn(Optional.ofNullable(profileImage));

        Resource resource = profileImageService.downloadFile(user.getUsername());

        verify(profileImageRepository, times(1)).findByPortal(portal);
    }

    @Test
    void getFileById() {
}

    @Test
    void fileExistsById() {
        when(profileImageRepository.existsById(1L)).thenReturn(true);
        boolean yes = profileImageService.fileExistsById(1L);
        assertTrue(yes);
    }
}
package com.willpowered.eindprojectwpsbe.User;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationInputDto;
import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Exception.UserNotFoundException;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.ProfileImage.ProfileImage;
import com.willpowered.eindprojectwpsbe.ProfileImage.ProfileImageRepository;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserInputDto;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {


    @InjectMocks
    private UserService userService;

    @Mock
    UserRepository userRepository;
    @Mock
    PortalRepository portalRepository;
    @Mock
    ProfileImageRepository profileImageRepository;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    ProfileImage profileImage;
    @Mock
    Portal portal;

    private User firstUser;
    private User secondUser;
    private User thirdUser;
    private Authority userAuthority;
    private Authority secondUserAuthority;
    private Authority thirdUserAuthority;
    private Set<Authority> authorities;
    private Pageable pageable;

    @BeforeEach
    void setUp() {
        this.pageable = PageRequest.of(1, 10, Sort.by("authorities, username"));
        this.authorities = new HashSet<>();
        Set<Authority> emptyAuthorities = new HashSet<>();
        this.userAuthority = new Authority("firstUser", "ROLE_USER");
        this.secondUserAuthority = new Authority("firstUser", "ROLE_SUPER_USER");
        this.thirdUserAuthority = new Authority("firstUser", "ROLE_USER");
        authorities.addAll(Stream.of(userAuthority, secondUserAuthority, thirdUserAuthority).collect(Collectors.toList()));
        String encryptedPassword = passwordEncoder.encode("password");


        this.firstUser = new User("firstUser", encryptedPassword, true, "user@user.nl", authorities);
        this.secondUser = new User("secondUser", encryptedPassword, true, "user@user.nl", emptyAuthorities);
        this.thirdUser = new User("thirdUser", encryptedPassword, true, "user@user.nl", emptyAuthorities);
    }

    @Test
    void getUsers() {
        List<User> testUsers = Stream.of(firstUser, secondUser, thirdUser).collect(Collectors.toList());
        when(userRepository.findAll()).thenReturn(testUsers);

        List<User> users = userService.getUsers();

        verify(userRepository, times(1)).findAll();

        assertThat(users.size()).isEqualTo(3);
        assertThat(users.get(0)).isEqualTo(firstUser);
        assertThat(users.get(1)).isEqualTo(secondUser);
        assertThat(users.get(2)).isEqualTo(thirdUser);
        assertThat(users.get(0).getUsername()).isEqualTo("firstUser");
        assertThat(new ArrayList<>(users.get(0).getAuthorities()).get(1).getUsername()).isEqualTo("firstUser");
    }

    @Test
    void getUser() {
        when(userRepository.findById(firstUser.getUsername())).thenReturn(java.util.Optional.ofNullable(firstUser));

        User foundUser = userService.getUser(firstUser.getUsername());

        assertThat(foundUser.getAuthorities()).isEqualTo(firstUser.getAuthorities());
    }

    @Test
    void getUsersByRole() {
    }

    @Test
    void userExists() {
        when(userRepository.existsById(firstUser.getUsername())).thenReturn(true);

        Boolean userExists = userService.userExists(firstUser.getUsername());

        assertThat(userExists).isEqualTo(true);
    }

    @Test
    void createUser() {
        UserInputDto dto = UserInputDto.builder()
                .password("password")
                .email("user@user.nl")
                .authorities(Stream.of("ADMIN")
                        .collect(Collectors.toCollection(HashSet::new)))
                .build();

        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(firstUser);

        User repoCreated = userRepository.save(firstUser);
        String createdUserUsername = userService.createUser(dto);

        verify(userRepository).save(firstUser);
        assertThat(createdUserUsername).isSameAs(repoCreated.getUsername());
    }


    @Test
    void registerUser() {
        AuthenticationInputDto dto = new AuthenticationInputDto();
        dto.setPassword("password");
        dto.setUsername("firstUser");
        dto.setEmail("user@user.nl");

        when(userRepository.findById(firstUser.getUsername())).thenReturn(java.util.Optional.ofNullable(firstUser));
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(firstUser);
        when(profileImageRepository.save(any())).thenReturn(profileImage);
        when(portalRepository.save(any())).thenReturn(portal);

        User repoCreated = userRepository.save(firstUser);
        verify(userRepository).save(firstUser);
        String createdUserUsername = userService.registerUser(dto);


        User foundUser = userService.getUser(createdUserUsername);
        assertThat(createdUserUsername).isSameAs(foundUser.getUsername());
    }

    @Test
    void deleteUser() {
        when(userRepository.existsById(firstUser.getUsername())).thenReturn(true);

        userService.deleteUser(firstUser.getUsername());

        verify(userRepository).deleteById(firstUser.getUsername());
    }

    @Test
    public void deleteUserException() {
        assertThrows(UserNotFoundException.class, () -> userService.deleteUser(null));
    }


    @Test
    void setPassword() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getAuthorities() {
    }

    @Test
    void addAuthority() {
    }

    @Test
    void removeAuthority() {
    }
}
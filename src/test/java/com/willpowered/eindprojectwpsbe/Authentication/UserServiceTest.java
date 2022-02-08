package com.willpowered.eindprojectwpsbe.Authentication;

import com.willpowered.eindprojectwpsbe.Authority.Authority;
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

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Captor
    ArgumentCaptor<User> userArgumentCaptor;

    @Captor
    ArgumentCaptor<User> userDtoCaptor;

    @Captor
    ArgumentCaptor<Authority> authorityArgumentCaptor;

    @Mock
    private PasswordEncoder passwordEncoder;

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
//        List<User> userList = new ArrayList<>();
//        userList.add(firstUser);
//        when(userRepository.findByAuthoritiesContains(userAuthority.getAuthority(), pageable)).thenReturn(userList);
//
//        List<User> foundUsers = userService.getUsersByRole("ROLE_USER", pageable);
//
//        assertThat(foundUsers.get(0).getAuthorities().stream().map(p -> p.getAuthority().equals("ROLE_USER")));
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
        AuthenticationRequestDto dto = new AuthenticationRequestDto();
        dto.setPassword("password");
        dto.setUsername("firstUser");
        dto.setEmail("user@user.nl");

        when(userRepository.findById(firstUser.getUsername())).thenReturn(java.util.Optional.ofNullable(firstUser));
        when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(firstUser);

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
//        String username = "firstUser";
//        String password = "password";
//        String encodedPassword = passwordEncoder.encode(password);
//        Authentication authentication = Mockito.mock(Authentication.class);
//        SecurityContext securityContext = Mockito.mock(SecurityContext.class);
//
//        when(userRepository.findById(firstUser.getUsername())).thenReturn(Optional.ofNullable(firstUser));
//        when(userRepository.save(firstUser)).thenReturn(firstUser);
//        when(securityContext.getAuthentication()).thenReturn(authentication);
//        SecurityContextHolder.setContext(securityContext);
//
//        asserThat(userService.setPassword(username, password).assertThrows
//            assertThrows(RecordNotFoundException.class, () -> machineService.getMachine(null));
//        }
//
//        verify(userRepository).save(firstUser);
//        assertThat(createdUserUsername).isSameAs(repoCreated.getUsername());
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
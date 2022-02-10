package com.willpowered.eindprojectwpsbe.Authority;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationInputDto;
import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Security.JwtUtil;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorityServiceTest {

    @InjectMocks
    private AuthorityService authorityService;
    @Mock
    UserRepository userRepository;
    @Mock
    Authority authority;
    @Mock
    Set<Authority> authorities;

    @Captor
    ArgumentCaptor<User> userCaptor;

    private User targetUser;
    private User currentUser;
    private AuthenticationInputDto inputDto;
    private UsernamePasswordAuthenticationToken authToken;

    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();


        targetUser = User.builder()
                .username("targetUser")
                .password("password")
                .email("email@targetuser.nl")
                .authorities(authorities)
                .build();
        authorities.add(authority);

        Authority newAuthority = new Authority(targetUser.getUsername(), "de baas");
        Set<Authority> newAuthorities = new HashSet<>();
        newAuthorities.add(newAuthority);

        currentUser = User.builder()
                .username("currentUser")
                .password("password")
                .email("email@currentuser.nl")
                .authorities(newAuthorities)
                .build();
 }

    //////////////////////////////
    //// Create


    @Test
    void addAuthority() {
        when(userRepository.existsById(targetUser.getUsername())).thenReturn(true);
        when(userRepository.findById(targetUser.getUsername())).thenReturn(java.util.Optional.ofNullable(targetUser));
        when(userRepository.save(targetUser)).thenReturn(currentUser);

        authorityService.addAuthority(targetUser.getUsername(), "de baas");

        verify(userRepository, times(1)).save(targetUser);
        assertThat(targetUser.getAuthorities()).isNotEqualTo(currentUser.getAuthorities());
    }

    //////////////////////////////
    //// Read

    @Test
    void getAuthorities() {
        when(userRepository.existsById(targetUser.getUsername())).thenReturn(true);
        when(userRepository.findById(targetUser.getUsername())).thenReturn(java.util.Optional.ofNullable(targetUser));

        Set<Authority> servicedAuthorities = authorityService.getAuthorities(targetUser.getUsername());

        verify(userRepository, times(1)).existsById(targetUser.getUsername());
        verify(userRepository, times(1)).findById(targetUser.getUsername());
        assertThat(servicedAuthorities).isEqualTo(targetUser.getAuthorities());
    }

    @Test
    void removeAuthority() {
        when(userRepository.existsById(currentUser.getUsername())).thenReturn(true);
        when(userRepository.findById(currentUser.getUsername())).thenReturn(java.util.Optional.ofNullable(currentUser));

        authorityService.removeAuthority(currentUser.getUsername(), "de baas");

        verify(userRepository, times(1)).existsById(currentUser.getUsername());
        verify(userRepository, times(1)).findById(currentUser.getUsername());
        verify(userRepository, times(1)).save(userCaptor.capture());
        User currentUserValues = userCaptor.getValue();

        assertThat(currentUserValues.getAuthorities()).isEqualTo(currentUser.getAuthorities());
    }
}
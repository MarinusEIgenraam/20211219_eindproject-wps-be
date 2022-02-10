package com.willpowered.eindprojectwpsbe.Authentication;

import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Security.JwtUtil;
import com.willpowered.eindprojectwpsbe.User.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.naming.Context;
import java.security.Principal;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthenticationServiceTest {

    @InjectMocks
    AuthenticationService authenticationService;
    @Mock
    UserDetailsService userDetailsService;

    @Mock
    UserDetails userDetails;
    @Mock
    Authentication authentication;
    @Mock
    SecurityContext securityContext;
    @Mock
    Principal principal;

    @Mock
    AuthenticationManager authenticationManager;
    @Mock
    JwtUtil jwtUtil;
    @Mock
    Context context;


    private AuthenticationInputDto inputDto;
    private UsernamePasswordAuthenticationToken authToken;
    User firstUser;
    User secondUser;

    @BeforeEach
    void setUp() {

        Set<Authority> authorities = new HashSet<>();

        User firstUser = User.builder()
                .username("firstUser")
                .password("password")
                .email("email@targetuser.nl")
                .build();
        User secondUser = User.builder()
                .username("secondUser")
                .password("password")
                .email("email@currentuser.nl")
                .build();
        inputDto = new AuthenticationInputDto(
                "dtoUser",
                "dtoPassword",
                "email@dtoUser.nl");
        authToken = new UsernamePasswordAuthenticationToken(inputDto.getUsername(), inputDto.getPassword());


    }


    //////////////////////////////
    //// Read

    @Test
    void authenticateUser() {
        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            when(authenticationManager.authenticate(authToken)).thenReturn(authToken);
            when(userDetailsService.loadUserByUsername(inputDto.getUsername())).thenReturn(userDetails);
            when(jwtUtil.generateToken(userDetails)).thenReturn("BestTokenEver");

            AuthenticationDto authDto = authenticationService.authenticateUser(inputDto);

            verify(userDetailsService, times(1)).loadUserByUsername(inputDto.getUsername());
            verify(jwtUtil, times(1)).generateToken(userDetails);
            assertThat(authDto.getJwt()).isEqualTo("BestTokenEver");
        }
    }


    @Test
    void getCurrentUser() {
        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.isAuthenticated()).thenReturn(true);

            Authentication newAuthentication = authenticationService.getCurrentUser();

            verify(securityContext, times(1)).getAuthentication();
            if (newAuthentication != null) {
                assertTrue(newAuthentication.isAuthenticated());
            }
        }
    }


    @Test
    void getCurrentUserName() {
        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);

            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.getPrincipal()).thenReturn(userDetails);
            when(userDetails.getUsername()).thenReturn("ownWindows","swodniWnwo");

            String detailName = authenticationService.getCurrentUsername();
            assertThat(detailName).isEqualTo("ownWindows");

            String principalName = authenticationService.getCurrentUsername();
            assertThat(principalName).isEqualTo("swodniWnwo");
        }
    }

    @Test
    void isLoggedIn() {
        try (MockedStatic<SecurityContextHolder> securityContextHolder = Mockito.mockStatic(SecurityContextHolder.class)) {
            securityContextHolder.when(SecurityContextHolder::getContext).thenReturn(securityContext);
            when(securityContext.getAuthentication()).thenReturn(authentication);
            when(authentication.isAuthenticated()).thenReturn(true, false);

            assertTrue(authenticationService.isLoggedIn());
            Assertions.assertFalse(authenticationService.isLoggedIn());
        }
    }
}
package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.auth.Authority;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import lombok.var;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertServiceTest {

    @Mock
    PortalRepository portalRepository;
    @Mock
    AlertRepository alertRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    private AlertService alertService;
    @InjectMocks
    private UserAuthenticateService userAuthenticateService;

    @Captor
    ArgumentCaptor<Alert> alertArgumentCaptor;

    private Alert firstAlert;
    private Alert secondAlert;
    private Alert thirdAlert;
    private User firstUser;
    private User secondUser;
    private User thirdUser;
    private Portal firstPortal;
    private Portal secondPortal;
    private Portal thirdPortal;
    private Authentication authentication;
    private List<Alert> firstAlertList;
    private org.springframework.data.domain.Pageable pageable;

    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();
        this.firstAlertList = new ArrayList<>();
        this.authentication = Mockito.mock(Authentication.class);
        this.firstUser = new User("firstUser", "password", true, "user@user.nl", authorities);
        this.secondUser = new User("secondUser", "password", true, "user@user.nl", authorities);
        this.thirdUser = new User("thirdUser", "password", true, "user@user.nl", authorities);
        this.firstPortal = new Portal(1L, firstAlertList, firstUser);
        this.secondPortal = new Portal(1L, firstAlertList, secondUser);
        this.thirdPortal = new Portal(1L, firstAlertList, thirdUser);
        this.firstAlert = new Alert(1L, "First alert", "Better check alerts", LocalDate.parse("2019-05-01"), firstPortal);
        this.secondAlert = new Alert(2L, "Second alert", "Better check alerts", LocalDate.parse("2021-10-01"), firstPortal);
        this.thirdAlert = new Alert(3L, "Third alert", "Better check alerts", LocalDate.parse("2021-11-02"), firstPortal);
        firstAlert.setId(1L);
        firstAlertList.add(firstAlert);
        firstAlertList.add(secondAlert);
        firstAlertList.add(thirdAlert);
        this.firstPortal = new Portal(1L, firstAlertList, firstUser);
    }

    @AfterEach
    void after() {
        alertRepository.deleteAll();
    }


    @Test
    void getAlert() {
        when(alertRepository.findById(1L)).thenReturn(Optional.of(firstAlert));

        alertService.getAlert(1L);
    }

    @Test
    void getAlertsForUser() {
        when(userRepository.findById(firstUser.getUsername())).thenReturn(Optional.ofNullable(firstUser));

        when(portalRepository.findByUser(firstUser)).thenReturn((Optional.ofNullable(firstPortal)));

        when(alertRepository.findAllByPortal(firstPortal, pageable)).thenReturn(firstAlertList);

        List<Alert> alerts = alertService.getAlertsForUser(firstUser.getUsername(), pageable);
        verify(userRepository, times(1)).findById(firstUser.getUsername());
        verify(portalRepository, times(1)).findByUser(firstUser);
        verify(portalRepository, times(1)).findByUser(firstUser);

        assertThat(alerts.get(1).getId()).isEqualTo(firstAlertList.get(1).getId());

    }


//    @Test
//    void addAlert() {
//        org.springframework.security.core.userdetails.User principal = (org.springframework.security.core.userdetails.User) SecurityContextHolder.
//                getContext().getAuthentication().getPrincipal();
//        User user = new User();
//        user.setUsername("firstUser");
//        String title = "Comment on comment";
//        when(portalRepository.findByUser(firstUser)).thenReturn(Optional.ofNullable(firstPortal));
//        when(alertRepository.save(firstAlert)).thenReturn(firstAlert);
//        when(userAuthenticateService.getCurrentUser()).thenReturn(firstUser);
//        when(userRepository.findByUsername(principal.getUsername())).thenReturn(Optional.ofNullable(secondUser));
//
//
//        Alert newAlert = alertService.addAlert(title, user);
//        verify(alertRepository, times(1)).save(alertArgumentCaptor.capture());
//        verify(userAuthenticateService, times(1)).getCurrentUser();
//        Alert captured = alertArgumentCaptor.getValue();
//
//        assertThat(captured.getId()).isEqualTo(1);
//        assertThat(captured.getTitle()).isEqualTo("Comment on comment");
//
//
//    }

    @Test
    void saveAlert() {
        alertRepository.save(firstAlert);
        verify(alertRepository, times(1)).save(alertArgumentCaptor.capture());
        var alertFirst = alertArgumentCaptor.getValue();

        assertThat(alertFirst.getId()).isEqualTo(1);
        assertThat(alertFirst.getTitle()).isEqualTo("First alert");
        assertThat(alertFirst.getText()).isEqualTo("Better check alerts");
    }


    @Test
    void updateAlert() {
        when(alertRepository.findById(1L)).thenReturn(Optional.ofNullable(firstAlert));

        firstAlert.setTitle("A new alert title");
        firstAlert.setText("A new alert text");
        alertService.updateAlert(1L, firstAlert);
        verify(alertRepository).save(firstAlert);

        Optional<Alert> optionalAlert = alertRepository.findById(1L);
        Alert updatedAlert = optionalAlert.get();

        assertThat(updatedAlert.getId()).isEqualTo(1);
        assertThat(updatedAlert.getTitle()).isEqualTo("A new alert title");
        assertThat(updatedAlert.getText()).isEqualTo("A new alert text");
    }

    @Test
    void deleteAlert() {
        alertRepository.delete(firstAlert);
        alertService.deleteAlert(1L);

        verify(alertRepository, times(1)).delete(firstAlert);
    }
}
package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    @Mock
    private AuthenticationService authenticationService;
    @Mock
    Pageable pageable;

    @InjectMocks
    private AlertService alertService;

    @Captor
    ArgumentCaptor<Alert> alertCaptor;


    private User targetUser;
    private User currentUser;
    private Portal portalTarget;
    private Portal portalCurrent;
    private Alert firstAlert;
    private Alert secondAlert;
    private List<Alert> alertList;


    @BeforeEach
    void setUp() {
        targetUser = User.builder()
                .username("targetUser")
                .password("password")
                .build();
        currentUser = User.builder()
                .username("currentUser")
                .password("password")
                .build();

        portalTarget = Portal.builder()
                .id(1L)
                .portalOwner(targetUser)
                .build();
        portalCurrent = Portal.builder()
                .id(2L)
                .portalOwner(currentUser)
                .build();

        firstAlert = Alert.builder()
                .id(1L)
                .title("Task invitation")
                .text("")
                .createdAt(LocalDate.parse("2022-02-08"))
                .portal(portalTarget)
                .build();
        secondAlert = Alert.builder()
                .id(1L)
                .title("Task invitation")
                .text("currentUser has invited you to work on a task")
                .createdAt(LocalDate.parse("2022-02-08"))
                .portal(portalTarget)
                .build();



        alertList = Arrays.asList(firstAlert, secondAlert);
    }

    @AfterEach
    void after() {
        alertRepository.deleteAll();
        portalRepository.deleteAll();
        alertRepository.deleteAll();
    }

    //////////////////////////////
    //// Create

    @Test
    void addAlert() {
        when(portalRepository.findByPortalOwner(targetUser)).thenReturn((Optional.ofNullable(portalTarget)));
        when(authenticationService.getCurrentUsername()).thenReturn(currentUser.getUsername());
        when(alertRepository.save(any())).thenReturn(secondAlert);

        Alert alert = alertService.addAlert(firstAlert.getTitle(), targetUser);

        verify(alertRepository, times(1)).save(alertCaptor.capture());
        Alert addedAlert = alertCaptor.getValue();

        assertThat(alert.getText()).isEqualTo(addedAlert.getText());
    }

    @Test
    void saveAlert() {
        AlertInputDto dto = AlertInputDto.builder()
                .id(1L)
                .title("Task invitation")
                .text("")
                .createdAt(LocalDate.parse("2022-02-08"))
                .portalId(1L)
                .build();

        alertService.saveAlert(dto);
        verify(alertRepository, times(1)).save(alertCaptor.capture());
        var alertFirst = alertCaptor.getValue();

        assertThat(alertFirst.getId()).isEqualTo(dto.id);
        assertThat(alertFirst.getTitle()).isEqualTo(dto.title);
        assertThat(alertFirst.getText()).isEqualTo(dto.text);
    }

    //////////////////////////////
    //// Read

    @Test
    void getAlert() {
        when(alertRepository.findById(1L)).thenReturn(Optional.ofNullable(firstAlert));

        Alert newAllert = alertService.getAlert(1L);

        verify(alertRepository, times(1)).findById(1L);
        assertThat(newAllert).isEqualTo(firstAlert);

    }

    @Test
    void getAlertsForUser() {
        when(userRepository.findById(targetUser.getUsername())).thenReturn(Optional.ofNullable(targetUser));
        when(portalRepository.findByPortalOwner(targetUser)).thenReturn(Optional.ofNullable(portalTarget));
        when(alertRepository.findAllByPortal(portalTarget, pageable)).thenReturn(Arrays.asList(firstAlert, secondAlert));

        List<Alert> alerts = alertService.getAlertsForUser(targetUser.getUsername(), pageable);

        verify(userRepository, times(1)).findById(targetUser.getUsername());
        verify(portalRepository, times(1)).findByPortalOwner(targetUser);
        verify(alertRepository, times(1)).findAllByPortal(portalTarget, pageable);
        assertThat(alerts.get(1).getId()).isEqualTo(alertList.get(1).getId());
        assertThat(alerts.get(1).getTitle()).isEqualTo("Task invitation");
    }

    //////////////////////////////
    //// Update

    @Test
    void updateAlert() {
        when(alertRepository.findById(1L)).thenReturn(Optional.ofNullable(firstAlert));
        secondAlert.setId(1L);

        alertService.updateAlert(1L, secondAlert);

        verify(alertRepository, times(1)).save(alertCaptor.capture());
        var alertSaved = alertCaptor.getValue();
        assertThat(alertSaved.getId()).isEqualTo(1);
        assertThat(alertSaved.getTitle()).isEqualTo(secondAlert.getTitle());
    }

    //////////////////////////////
    //// Update

    @Test
    void deleteAlert() {
        when(alertRepository.findById(1L)).thenReturn(Optional.ofNullable(firstAlert));

        alertService.deleteAlert(1L);

        verify(alertRepository, times(1)).deleteById(1L);
    }

}
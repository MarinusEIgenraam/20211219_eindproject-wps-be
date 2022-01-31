package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.auth.Authority;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import lombok.var;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private List<Alert> firstAlertList;

    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();
        this.firstAlertList = new ArrayList<>();
        this.firstUser = new User("firstUser", "password", true, "user@user.nl", authorities);
        this.secondUser = new User("secondUser", "password", true, "user@user.nl", authorities);
        this.thirdUser = new User("thirdUser", "password", true, "user@user.nl", authorities);
        this.firstAlert = new Alert(1L, "First alert", "Better check alerts", firstPortal);
        this.secondAlert = new Alert(2L,"Second alert", "Better check alerts", firstPortal);
        this.thirdAlert = new Alert(3L,"Third alert", "Better check alerts", firstPortal);
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
        when(portalRepository.findByUser(firstUser)).thenReturn(Optional.ofNullable(firstPortal));
        when(alertRepository.findAllByPortal(firstPortal)).thenReturn(firstAlertList);
        when(userRepository.findById(firstUser.getUsername())).thenReturn(Optional.ofNullable(firstUser));
        alertService.getAlertsForUser("firstUser");
    }

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
    void addAlert() {
        String title = "This is a test";
        String text = "This is not a test";
        when(portalRepository.findByUser(firstUser)).thenReturn(Optional.ofNullable(firstPortal));

        alertService.addAlert(title, text, firstUser);
        verify(alertRepository, times(1)).save(alertArgumentCaptor.capture());
        var alertFirst = alertArgumentCaptor.getValue();

        assertThat(alertFirst.getTitle()).isEqualTo("This is a test");
        assertThat(alertFirst.getText()).isEqualTo("This is not a test");
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
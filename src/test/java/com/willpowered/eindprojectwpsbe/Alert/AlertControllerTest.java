package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalDto;
import com.willpowered.eindprojectwpsbe.User.User;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlertControllerTest {

    @InjectMocks
    AlertController alertController;


    @Mock
    AlertService alertService;


    @Captor
    ArgumentCaptor<AlertInputDto> alertInputDtoCaptor;


    @Mock
    List<Alert> firstAlertList;
    @Mock
    PortalDto firstPortalDto;
    @Mock
    User firstUser;
    @Mock
    Pageable pageable;


    Alert firstAlert;
    AlertInputDto firstAlertInputDto;
    AlertDto firstAlertDto;

    Portal firstPortal;

    @BeforeEach
    void setUp() {
        firstPortal = Portal.builder()
                .id(1L)
                .portalOwner(firstUser)
                .alertList(firstAlertList)
                .build();
        firstAlert = Alert.builder()
                .id(1L)
                .title("Task invitation")
                .text("currentUser has invited you to work on a task")
                .createdAt(LocalDate.parse("2022-02-08"))
                .portal(firstPortal)
                .build();
        firstAlertInputDto = AlertInputDto.builder()
                .id(1L)
                .portalId(1L)
                .title("Task invitation")
                .text("currentUser has invited you to work on a task")
                .createdAt(LocalDate.parse("2022-02-08"))
                .build();
        firstAlertDto = AlertDto.fromAlert(firstAlert);

    }


    //////////////////////////////
    //// Create

    @Test
    void saveAlert() {
        when(alertService.saveAlert(firstAlertInputDto)).thenReturn(firstAlert);

        alertController.saveAlert(firstAlertInputDto);

        verify(alertService, times(1)).saveAlert(alertInputDtoCaptor.capture());
        var capturedAlertInputDto = alertInputDtoCaptor.getValue();
        assertEquals(firstAlertInputDto, capturedAlertInputDto);
    }

    //////////////////////////////
    //// Read


    @Test
    void getAlert() {
        when(alertService.getAlert(1L)).thenReturn(firstAlert);

        alertController.getAlert(1L);

        verify(alertService, times(1)).getAlert(1L);
    }

    @Test
    void getAllAlertsForUser() {
        List<Alert> foundAlertList = List.of(firstAlert);
        when(alertService.getAlertsForUser(any(), any())).thenReturn(foundAlertList);

        alertController.getAllAlertsForUser("username", 0, 1, 10, (new String[]{"createdAt"}));

        verify(alertService, times(1)).getAlertsForUser(any(), any());
    }


    //////////////////////////////
    //// Delete


    @Test
    void deleteAlert() {
        alertController.deleteAlert(1L);

        verify(alertService, times(1)).deleteAlert(1l);
    }
}
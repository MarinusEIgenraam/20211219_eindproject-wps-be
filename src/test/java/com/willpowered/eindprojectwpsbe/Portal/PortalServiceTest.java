package com.willpowered.eindprojectwpsbe.Portal;

import com.willpowered.eindprojectwpsbe.Alert.Alert;
import com.willpowered.eindprojectwpsbe.auth.Authority;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PortalServiceTest {

    @Mock
    PortalRepository portalRepository;

    @InjectMocks
    private PortalService portalService;

    @Captor
    ArgumentCaptor<Portal> portalArgumentCaptor;

    private Portal firstPortal;
    private Portal secondPortal;
    private Portal thirdPortal;
    private User firstUser;
    private User secondUser;


    @BeforeEach
    void setUp() {
        Set<Authority> authorities = new HashSet<>();
        this.firstUser = new User("firstUser", "password", true, "user@user.nl", authorities);
        this.secondUser = new User("secondUser", "password", true, "user@user.nl", authorities);
        User thirdUser = new User("thirdUser", "password", true, "user@user.nl", authorities);
        Alert firstAlert = new Alert("First alert", "Better check alerts");Alert secondAlert = new Alert("Second alert", "Better check alerts");Alert thirdAlert = new Alert("Third alert", "Better check alerts");
        List<Alert> firstAlertList = new ArrayList<>();
        firstAlertList.add(firstAlert);
        List<Alert> secondAlertList = new ArrayList<>();
        secondAlertList.add(secondAlert);
        List<Alert>thirdAlertList = new ArrayList<>();
        thirdAlertList.add(thirdAlert);
        this.firstPortal = new Portal(1L,firstAlertList, firstUser);
        this.secondPortal = new Portal(2L, secondAlertList, secondUser);
        this.thirdPortal = new Portal(3L,thirdAlertList, thirdUser);
    }

    @Test
    void deletePortal() {
        portalRepository.delete(firstPortal);
        portalService.deletePortal(1L);
        verify(portalRepository, times(1)).delete(firstPortal);
    }

    @Test
    void updatePortal() {
        when(portalRepository.findById(1L)).thenReturn(Optional.of(firstPortal));
        firstPortal.setUser(secondUser);
        portalService.updatePortal(1l, firstPortal);
        verify(portalRepository).save(firstPortal);

        assertThat(firstPortal.getId()).isEqualTo(1);
        assertThat(firstPortal.getUser().getUsername()).isEqualTo("secondUser");
    }

    @Test
    void getUserPortal() {
        when(portalRepository.findByUser(firstUser)).thenReturn(Optional.of(firstPortal));
        portalService.getUserPortal(firstUser);
    }

    @Test
    void savePortal() {
        portalRepository.save(firstPortal);
        verify(portalRepository, times(1)).save(portalArgumentCaptor.capture());

        assertThat(firstPortal.getUser().getUsername()).isEqualTo("firstUser");
        assertThat(firstPortal.getAlertList().get(0).getTitle()).isEqualTo("First alert");
    }

    @Test
    void getPortal() {
        when(portalRepository.findById(1L)).thenReturn(Optional.of(firstPortal));

        portalService.getPortal(1L);
    }

    @Test
    void getPortals() {
        List<Portal> testPortals = new ArrayList<>();
        testPortals.add(firstPortal);
        testPortals.add(secondPortal);
        testPortals.add(thirdPortal);

        when(portalRepository.findAll()).thenReturn(testPortals);

        portalService.getPortals();

        verify(portalRepository, times(1)).findAll();

        assertThat(testPortals.size()).isEqualTo(3);
        assertThat(testPortals.get(0)).isEqualTo(firstPortal);
        assertThat(testPortals.get(1)).isEqualTo(secondPortal);
        assertThat(testPortals.get(2)).isEqualTo(thirdPortal);
    }

    @Test
    public void getPortalExceptionTest() {
        assertThrows(RecordNotFoundException.class, () -> portalService.getPortal(null));
    }

}
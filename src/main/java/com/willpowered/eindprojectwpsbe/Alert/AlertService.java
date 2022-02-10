package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class AlertService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private PortalRepository portalRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationService authenticationService;

    //////////////////////////////
    //// Create

    public Alert addAlert(String title, User user) {
        var optionalPortal = portalRepository.findByUser(user);
        var portal = optionalPortal.get();
        String currentUserName = authenticationService.getCurrentUsername();

        var alert = new Alert();
        alert.setPortal(portal);
        alert.setCreatedAt(LocalDate.now());
        if (Objects.equals(title, "Comment on comment")) {
            alert.setText(currentUserName + " has commented on your comment");
        } else if (Objects.equals(title, "Comment on project")) {
            alert.setText(currentUserName + " commented on your project ");
        } else if (Objects.equals(title, "Comment on blog")) {
            alert.setText(currentUserName + " has commented on your blog");
        } else if (Objects.equals(title, "Task invitation")) {
            alert.setText(currentUserName + " has invited you to work on a task");
        } else if (Objects.equals(title, "Project invitation")) {
            alert.setText(currentUserName + " has invited you to work on a project");
        }
        alert.setTitle(title);
        return alertRepository.save(alert);
    }

    public Alert saveAlert(AlertInputDto dto) {
        var optionalPortal = portalRepository.findById(dto.portalId);
        Alert alert = dto.toAlert();
        if (optionalPortal.isPresent()) {
            Portal portal = optionalPortal.get();
            alert.setPortal(portal);
        }
        return alertRepository.save(alert);
    }

    //////////////////////////////
    //// Read

    public Alert getAlert(Long id) {
        var alert = alertRepository.findById(id);

        if (alert.isPresent()) {
            return alert.get();
        } else {
            throw new RecordNotFoundException("Alert does not exist");
        }
    }

    public List<Alert> getAlertsForUser(String username, Pageable pageable) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            var optionalPortal = portalRepository.findByUser(user);
            if (optionalPortal.isPresent()) {
                var portal = optionalPortal.get();
                return alertRepository.findAllByPortal(portal, pageable);
            } else {
                throw new RecordNotFoundException("This user has no portal");
            }
        } else {
            throw new RecordNotFoundException("This portal has no open alerts");
        }
    }


    //////////////////////////////
    //// Update

    public void updateAlert(Long id, Alert alert) {
        var optionalAlert = alertRepository.findById(id);
        if (optionalAlert.isPresent()) {
            alertRepository.save(alert);
        } else {
            throw new RecordNotFoundException("Alert does not exist");
        }
    }

    //////////////////////////////
    //// Delete

    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }


}

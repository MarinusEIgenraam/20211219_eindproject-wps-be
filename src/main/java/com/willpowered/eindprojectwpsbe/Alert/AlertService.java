package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import com.willpowered.eindprojectwpsbe.Portal.PortalRepository;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlertService {

    private static String PROJECT_URL = "";

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;
    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private PortalRepository portalRepository;


    public Alert getAlert(Long id) {
        Optional<Alert> alert = alertRepository.findById(id);

        if(alert.isPresent()) {
            return alert.get();
        } else {
            throw new RecordNotFoundException("Alert does not exist");
        }
    }

    public List<Alert> getAlertsForUser(String username) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            var user = optionalUser.get();
            var optionalPortal = portalRepository.findByUser(user);
            if (optionalPortal.isPresent()) {
                var portal = optionalPortal.get();
                return alertRepository.findAllByPortal(portal);
            } else {
                throw new RecordNotFoundException("This user has no portal");
            }
        } else {
            throw new RecordNotFoundException("This portal has no open alerts");
        }
    }

    public List<Alert> getAlertsForPortal(Long id) {
        var optionalPortal = portalRepository.findById(id);
        if (optionalPortal.isPresent()) {
            Portal portal = optionalPortal.get();
            return alertRepository.findAllByPortal(portal);
        } else {
            throw new RecordNotFoundException("This portal has no open alerts");
        }
    }

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public Alert addAlert(String title, String text, User user) {
        var optionalPortal = portalRepository.findByUser(user);
        var portal = optionalPortal.get();

        var alert = new Alert();
        alert.setPortal(portal);
        alert.setText(text);
        alert.setTitle(title);
        return alertRepository.save(alert);
    }

    public void updateAlert(Long id, Alert alert) {
        Optional<Alert> optionalAlert = alertRepository.findById(id);
        if (optionalAlert.isPresent()) {
            alertRepository.deleteById(id);
            alertRepository.save(alert);
        } else {
            throw new RecordNotFoundException("Alert does not exist");
        }
    }

    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }


}

package com.willpowered.eindprojectwpsbe.service.communication;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.AlertRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
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


    public List<Alert> getAlerts() {
        return alertRepository.findAll();
    }

    public Alert getAlert(Long id) {
        Optional<Alert> alert = alertRepository.findById(id);

        if(alert.isPresent()) {
            return alert.get();
        } else {
            throw new RecordNotFoundException("Machine does not exist");
        }
    }

    public Alert saveAlert(Alert alert) {
        return alertRepository.save(alert);
    }

    public void updateAlert(Long id, Alert alert) {
        Optional<Alert> optionalAlert = alertRepository.findById(id);
        if (optionalAlert.isPresent()) {
            alertRepository.deleteById(id);
            alertRepository.save(alert);
        } else {
            throw new RecordNotFoundException("alert does not exist");
        }
    }

    public void deleteAlert(Long id) {
        alertRepository.deleteById(id);
    }


}

package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Alert.AlertDto;
import com.willpowered.eindprojectwpsbe.dto.communication.Alert.AlertInputDto;
import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.service.communication.AlertService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/alerts")
@AllArgsConstructor
@Slf4j
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/{id}")
    public AlertDto getAlert(@PathVariable("id") Long id) {
        var alert = alertService.getAlert(id);
        return AlertDto.fromAlert(alert);
    }

    @GetMapping("/{username}")
    public List<AlertDto> getAllAlertsForUser(@PathVariable("username")String username) {
        var dtos = new ArrayList<AlertDto>();
        var alerts = alertService.getAlertsForUser(username);

        for (Alert alert : alerts) {
            dtos.add(AlertDto.fromAlert(alert));
        }
        return dtos;
    }

    @GetMapping("/portal/{id}")
    public List<AlertDto> getAllAlertsForPortal(@PathVariable("id")Long id) {
        var dtos = new ArrayList<AlertDto>();
        var alerts = alertService.getAlertsForPortal(id);

        for (Alert alert : alerts) {
            dtos.add(AlertDto.fromAlert(alert));
        }
        return dtos;
    }

    @PostMapping
    public AlertDto saveAlert(@RequestBody AlertInputDto Dto) {
        var alert = alertService.saveAlert(Dto.toAlert());
        return AlertDto.fromAlert(alert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable("id") Long id) {
        alertService.deleteAlert(id);
    }
}
package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Alert.AlertDTO;
import com.willpowered.eindprojectwpsbe.dto.communication.Alert.AlertInputDTO;
import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.service.communication.AlertService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/alerts")
@AllArgsConstructor
@Slf4j
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping("/{id}")
    public AlertDTO getAlert(@PathVariable("id") Long id) {
        var alert = alertService.getAlert(id);
        return AlertDTO.fromAlert(alert);
    }

    @PostMapping
    public AlertDTO saveAlert(@RequestBody AlertInputDTO dto) {
        var alert = alertService.saveAlert(dto.toAlert());
        return AlertDTO.fromAlert(alert);
    }

    @PutMapping("/{id}")
    public AlertDTO updateAlert(@PathVariable Long id, @RequestBody Alert alert) {
        alertService.updateAlert(id, alert);
        return AlertDTO.fromAlert(alert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable("id") Long id) {
        alertService.deleteAlert(id);
    }
}
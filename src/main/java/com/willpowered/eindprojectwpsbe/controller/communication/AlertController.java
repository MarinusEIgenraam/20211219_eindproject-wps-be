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

    @PostMapping
    public AlertDto saveAlert(@RequestBody AlertInputDto Dto) {
        var alert = alertService.saveAlert(Dto.toAlert());
        return AlertDto.fromAlert(alert);
    }

    @PutMapping("/{id}")
    public AlertDto updateAlert(@PathVariable Long id, @RequestBody Alert alert) {
        alertService.updateAlert(id, alert);
        return AlertDto.fromAlert(alert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable("id") Long id) {
        alertService.deleteAlert(id);
    }
}
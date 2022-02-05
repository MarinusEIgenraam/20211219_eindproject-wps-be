package com.willpowered.eindprojectwpsbe.Alert;

import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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

    @GetMapping("")
    public Page<AlertDto> getAllAlertsForUser(
            @RequestParam(value = "username")String username,
            @RequestParam(value = "page", defaultValue = "0") int page,
            @RequestParam(value = "number", defaultValue = "0") int number,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "sort", defaultValue = "createdAt") String[] sort
    ) {
        var dtos = new ArrayList<AlertDto>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());
        var alerts = alertService.getAlertsForUser(username, pageable);


        for (Alert alert : alerts) {
            dtos.add(AlertDto.fromAlert(alert));
        }

        return new PageImpl<>(dtos);
    }


    @PostMapping
    public AlertDto saveAlert(@RequestBody AlertInputDto dto) {
        Alert alert = alertService.saveAlert(dto);
        return AlertDto.fromAlert(alert);
    }

    @DeleteMapping("/{id}")
    public void deleteAlert(@PathVariable("id") Long id) {
        alertService.deleteAlert(id);
    }
}
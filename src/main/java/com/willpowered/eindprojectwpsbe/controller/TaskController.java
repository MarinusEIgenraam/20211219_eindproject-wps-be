package com.willpowered.eindprojectwpsbe.controller;

import com.willpowered.eindprojectwpsbe.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path ="/{id}")
    public ResponseEntity<Object> getTask(@PathVariable("id")Long id){
        return ResponseEntity.ok(taskService.getTask(id));
    }

}

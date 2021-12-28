package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.task.TaskResponse;
import com.willpowered.eindprojectwpsbe.service.elements.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.status;

@RestController
@AllArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping
    public ResponseEntity<Void> createTask(@RequestBody TaskRequest taskRequest) {
        taskService.save(taskRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAllTasks() {
        return status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskResponse> getTask(@PathVariable Long id) {
        return status(HttpStatus.OK).body(taskService.getTask(id));
    }

    @GetMapping("by-user/{username}")
    public ResponseEntity<List<TaskResponse>> getTasksByTaskOwner(@PathVariable String username) {
        return status(HttpStatus.OK).body(taskService.getTasksByTaskOwner(username));
    }

}

package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskDTO;
import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskInputDTO;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import com.willpowered.eindprojectwpsbe.service.elements.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public TaskDTO getTask(@PathVariable("id") Long id) {
        var task = taskService.getTask(id);
        return TaskDTO.fromTask(task);
    }

    @PostMapping
    public TaskDTO saveTask(@RequestBody TaskInputDTO dto) {
        var task = taskService.saveTask(dto.toTask());
        return TaskDTO.fromTask(task);
    }

    @PutMapping("/{id}")
    public TaskDTO updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return TaskDTO.fromTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}
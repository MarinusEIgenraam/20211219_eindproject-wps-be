package com.willpowered.eindprojectwpsbe.controller.elements;

import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Task.TaskInputDto;
import com.willpowered.eindprojectwpsbe.exception.BadRequestException;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import com.willpowered.eindprojectwpsbe.service.elements.TaskService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/tasks")
@AllArgsConstructor
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public TaskDto getTask(@PathVariable("id") Long id) {
        var task = taskService.getTask(id);
        return TaskDto.fromTask(task);
    }

    @GetMapping("/all")
    public List<TaskDto> getTasks() {
        var dtos = new ArrayList<TaskDto>();
        List<Task> tasks;

        tasks = taskService.getTasks();
        for (Task task : tasks) {
            dtos.add(TaskDto.fromTask(task));
        }
        return dtos;
    }


    @GetMapping
    public List<TaskDto> getTasks(
            @RequestParam(value = "parentProjectId", required = false) Long parentProjectId,
            @RequestParam(value = "parentTaskId", required = false) Long parentTaskId,
            @RequestParam(value = "taskOwner", required = false) String taskOwner
    ) {
        var dtos = new ArrayList<TaskDto>();

        List<Task> tasks;
        if (parentProjectId != null && parentTaskId == null && taskOwner == null) {
            tasks = taskService.getTasksForParentProject(parentProjectId);
        } else if  (parentProjectId == null && parentTaskId != null && taskOwner == null) {
            tasks = taskService.getTasksForParentTask(parentTaskId);
        } else if (parentProjectId == null && parentTaskId == null && taskOwner != null) {
            tasks = taskService.getTasksForTaskOwner(taskOwner);
        } else {
            throw new BadRequestException();
        }

        for (Task task : tasks) {
            dtos.add(TaskDto.fromTask(task));
        }

        return dtos;
    }

    @PostMapping
    public TaskDto saveTask(@RequestBody TaskInputDto dto) {
        return TaskDto.fromTask(taskService.saveTask(dto));
    }

    @PutMapping("/{id}")
    public TaskDto updateTask(@PathVariable Long id, @RequestBody Task task) {
        taskService.updateTask(id, task);
        return TaskDto.fromTask(task);
    }

    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable("id") Long id) {
        taskService.deleteTask(id);
    }
}
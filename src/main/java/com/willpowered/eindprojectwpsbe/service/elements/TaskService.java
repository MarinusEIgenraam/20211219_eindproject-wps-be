package com.willpowered.eindprojectwpsbe.service.elements;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import com.willpowered.eindprojectwpsbe.repository.elements.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public List<Task> findTasksByName(String name) {
        return taskRepository.findByTaskNameContainingIgnoreCase(name);}

    public Task getTask(Long id) {
        Optional<Task> task = taskRepository.findById(id);

        if(task.isPresent()) {
            return task.get();
        } else {
            throw new RecordNotFoundException("Machine does not exist");
        }
    }

    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    public void updateTask(Long id, Task task) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            taskRepository.deleteById(id);
            taskRepository.save(task);
        } else {
            throw new RecordNotFoundException("task does not exist");
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


}



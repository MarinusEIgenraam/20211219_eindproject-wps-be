package com.willpowered.eindprojectwpsbe.model.elements;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long taskId;

    @NotBlank(message = "Task name required")
    private String taskName;
    private String description;

    private Instant startTime;
    private Instant endTime;

    private boolean isRunning = true;

    @ManyToOne
    private User taskOwner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("tasks_tasks")
    private Task parentTask;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "tasks_tasks",
            joinColumns = @JoinColumn(name = "parent_task_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonManagedReference("tasks_task")
    private List<Task> taskTaskList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_project_id", nullable = false)
    @JsonManagedReference("projects_tasks")
    private Project parentProject;


}

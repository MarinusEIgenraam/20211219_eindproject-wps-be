package com.willpowered.eindprojectwpsbe.Task;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
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

    private LocalDate startTime;
    @Nullable
    private LocalDate editedTime;
    private LocalDate endTime;

    private Boolean isRunning = true;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.DETACH)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User taskOwner;

    @ManyToOne
    @JoinTable(
            name = "task_tasks",
            joinColumns = @JoinColumn(name = "parent_task"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonBackReference("task_tasks")
    private Task parentTask;


    @ManyToOne
    @JoinTable(
            name = "project_tasks",
            joinColumns = @JoinColumn(name = "parent_project"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonBackReference("project_tasks")
    private Project parentProject;

    @OneToMany(mappedBy = "parentTask", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("task_tasks")
    private List<Task> taskTaskList;
}
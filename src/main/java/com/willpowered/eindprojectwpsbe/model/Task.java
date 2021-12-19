package com.willpowered.eindprojectwpsbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.util.List;
import java.util.stream.LongStream;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Task name required")
    private String name;
    private String description;

    @ManyToOne(fetch = LAZY)
    @JsonBackReference("task_task")
    @JoinColumn(name = "parent_task_id", referencedColumnName = "id")
    private Task parentTask;

    @OneToMany
    @JsonManagedReference("task_task")
    private List<Task> taskList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference("project_task")
    @JoinColumn(name = "parent_project_id", referencedColumnName = "id")
    private Project parentProject;

}

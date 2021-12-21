package com.willpowered.eindprojectwpsbe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotBlank(message = "Project name can not be Null")
    private String name;

    private String description;
    private String url;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Size(max = 30, min = 1)
    @JoinTable(
            name = "projects_tasks",
            joinColumns = @JoinColumn(name = "parent_project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonManagedReference
    private List<Task> taskList = new ArrayList<>();



    @ManyToMany
    @JoinTable(
            name = "projects_categories",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    Set<Category> categories = new HashSet<>();
}

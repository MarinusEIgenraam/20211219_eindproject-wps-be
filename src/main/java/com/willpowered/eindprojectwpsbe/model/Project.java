package com.willpowered.eindprojectwpsbe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

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
    private boolean visible = true;

    @OneToMany
    @Size(max = 30, min = 1)
    @JsonManagedReference("project_task")
    private List<Task> taskList = new ArrayList<>();
}

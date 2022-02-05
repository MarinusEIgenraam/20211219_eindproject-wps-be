package com.willpowered.eindprojectwpsbe.Category;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.Project.Project;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @NotBlank(message = "Category name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @OneToMany(fetch = LAZY, cascade = CascadeType.REMOVE)
    @JoinTable(
            name = "category_projects",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonManagedReference("category_projects")
    private List<Project> projects;

}

package com.willpowered.eindprojectwpsbe.model.elements;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@Data
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

    @OneToMany(
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "project_categories",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    @JsonManagedReference
    private List<Project> projects;

}

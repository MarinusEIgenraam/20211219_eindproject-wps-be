package com.willpowered.eindprojectwpsbe.model.elements;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
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

    @Nullable
    private String url;

    @Nullable
    @Lob
    private String description;

    private LocalDateTime startTime;
    private LocalDateTime deadLine;

    private boolean isRunning = true;
    private Integer voteCount = 0;

    @ManyToOne
    private Category categoryName;

    @ManyToOne
    private User user;

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
            name = "projects_collaborators",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> collaborators = new ArrayList<>();


}

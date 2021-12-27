package com.willpowered.eindprojectwpsbe.model.elements;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long projectId;

    @NotBlank(message = "Project name can not be Null")
    private String projectName;

    @Nullable
    private String url;

    @Nullable
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private Instant startTime;
    private Instant deadLine;

    private Integer voteCount = 0;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "id", referencedColumnName = "id")
    private Category category;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_owner", referencedColumnName = "username")
    private User projectOwner;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Size(max = 30, min = 1)
    @JoinTable(
            name = "projects_tasks",
            joinColumns = @JoinColumn(name = "parent_project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonManagedReference("projects_tasks")
    private List<Task> projectTaskList;

    @ManyToMany
    @JoinTable(
            name = "projects_collaborators",
            joinColumns = @JoinColumn(name = "project_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> collaborators;


}

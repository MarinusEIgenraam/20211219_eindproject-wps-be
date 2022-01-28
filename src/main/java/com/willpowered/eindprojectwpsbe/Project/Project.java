package com.willpowered.eindprojectwpsbe.Project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.Category.Category;
import com.willpowered.eindprojectwpsbe.Task.Task;
import com.willpowered.eindprojectwpsbe.auth.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
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
    private String imageUrl;

    @Nullable
    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String description;

    private LocalDate startTime;
    @Nullable
    private LocalDate editedTime;
    private LocalDate endTime;

    private Integer voteCount = 0;

    private Boolean publiclyVisible;

    @ManyToOne(fetch = LAZY)
    @JsonBackReference("category_projects")
    private Category category;

    private Boolean isRunning = true;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "project_owner", referencedColumnName = "username")
    private User projectOwner;

    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @Size(max = 30, min = 1)
    @JoinTable(
            name = "project_tasks",
            joinColumns = @JoinColumn(name = "parent_project_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id"))
    @JsonManagedReference("project_tasks")
    private List<Task> projectTaskList;

    @ManyToMany
    @JoinTable(
            name = "project_collaborators",
            joinColumns = @JoinColumn(name = "projectId"),
            inverseJoinColumns = @JoinColumn(name = "username"))
    private List<User> collaborators;

}

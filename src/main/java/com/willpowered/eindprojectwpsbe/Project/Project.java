package com.willpowered.eindprojectwpsbe.Project;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.Category.Category;
import com.willpowered.eindprojectwpsbe.Task.Task;
import com.willpowered.eindprojectwpsbe.User.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Type;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
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

    @ManyToOne(fetch = LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "project_owner", referencedColumnName = "username")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private User projectOwner;

    @OneToMany(mappedBy = "parentProject", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @Size(max = 30, min = 1)
    @JsonManagedReference("project_tasks")
    private List<Task> projectTaskList;

    @ManyToMany(mappedBy = "projects")
    private Set<User> collaborators = new HashSet<>();


}

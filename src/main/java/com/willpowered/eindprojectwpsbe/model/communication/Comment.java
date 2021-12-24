package com.willpowered.eindprojectwpsbe.model.communication;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long id;

    @NotEmpty
    private String text;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", referencedColumnName = "id")
    private Project project;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JsonBackReference("comment_comment")
//    private Task parentComment;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "comments_comments",
            joinColumns = @JoinColumn(name = "parent_comment_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @JsonManagedReference("comment_comment")
    private List<Comment> commentList;
}

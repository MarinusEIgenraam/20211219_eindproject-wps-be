package com.willpowered.eindprojectwpsbe.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.Models.User;
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
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("comment_comments")
    private Comment parentComment;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "comments_comments",
            joinColumns = @JoinColumn(name = "parent_comment_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @JsonManagedReference("comments_comments")
    private List<Comment> commentList;
}

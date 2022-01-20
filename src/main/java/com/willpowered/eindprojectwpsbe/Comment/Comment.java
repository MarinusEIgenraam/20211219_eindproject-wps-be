package com.willpowered.eindprojectwpsbe.Comment;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sun.istack.Nullable;
import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;
import java.util.List;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.GenerationType.SEQUENCE;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotEmpty
    private String text;

    private LocalDateTime startTime;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "blogId", referencedColumnName = "blogId")
    private Blog blog;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "comment_owner", referencedColumnName = "username")
    private User user;

    @Nullable
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("comment_comments")
    private Comment parentComment;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "comment_comments",
            joinColumns = @JoinColumn(name = "parent_comment_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @JsonManagedReference("comments_comments")
    private List<Comment> commentList;
}

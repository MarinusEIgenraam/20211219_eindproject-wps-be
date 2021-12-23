package com.willpowered.eindprojectwpsbe.model.communication;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.Instant;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    private Long commentId;

    @NotEmpty
    private String text;

    private Instant createdDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_project_id")
    @JsonBackReference
    private Task parentProject;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("comment_comment")
    private Task parentComment;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "comments_comments",
            joinColumns = @JoinColumn(name = "parent_comment_id"),
            inverseJoinColumns = @JoinColumn(name = "comment_id"))
    @JsonManagedReference("comment_comment")
    private List<Comment> commentList;
}

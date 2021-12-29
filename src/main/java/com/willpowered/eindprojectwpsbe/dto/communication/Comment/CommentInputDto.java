package com.willpowered.eindprojectwpsbe.dto.communication.Comment;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import java.time.Instant;

public class CommentInputDto {

    public Long id;
    public String text;
    public Instant createdDate;
    public Project project;
    public User user;
    public Comment parentComment;

    public Comment toComment() {
        var comment = new Comment();

        comment.setId(id);
        comment.setText(text);
        comment.setCreatedDate(createdDate);
        comment.setProject(project);
        comment.setUser(user);
        comment.setParentComment(parentComment);

        return comment;
    }
}
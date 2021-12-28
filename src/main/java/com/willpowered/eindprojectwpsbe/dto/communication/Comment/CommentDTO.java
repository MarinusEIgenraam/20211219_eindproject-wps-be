package com.willpowered.eindprojectwpsbe.dto.communication.Comment;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import java.time.Instant;

public class CommentDTO {

    public Long id;
    public String text;
    public Instant createdDate;
    public Project project;
    public User user;
    public Comment parentComment;

    public static CommentDTO fromComment(Comment comment) {
        var dto = new CommentDTO();

        dto.id = comment.getId();
        dto.text = comment.getText();
        dto.createdDate = comment.getCreatedDate();
        dto.project = comment.getProject();
        dto.user = comment.getUser();
        dto.parentComment = comment.getParentComment();

        return dto;
    }


}
package com.willpowered.eindprojectwpsbe.dto.communication.Comment;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import java.time.Instant;

public class CommentDto {

    public Long id;
    public String text;
    public Instant createdDate;
    public Project project;
    public User user;
    public Comment parentComment;

    public static CommentDto fromComment(Comment comment) {
        var Dto = new CommentDto();

        Dto.id = comment.getId();
        Dto.text = comment.getText();
        Dto.createdDate = comment.getCreatedDate();
        Dto.project = comment.getProject();
        Dto.user = comment.getUser();
        Dto.parentComment = comment.getParentComment();

        return Dto;
    }


}
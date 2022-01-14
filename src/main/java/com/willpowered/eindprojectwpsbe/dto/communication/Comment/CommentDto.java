package com.willpowered.eindprojectwpsbe.dto.communication.Comment;

import com.willpowered.eindprojectwpsbe.dto.auth.User.UserDto;
import com.willpowered.eindprojectwpsbe.dto.elements.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import lombok.var;

import java.time.Instant;

public class CommentDto {

    public Long id;
    public String text;
    public Instant createdDate;
    public ProjectDto project;
    public UserDto user;
    public CommentDto parentComment;

    public static CommentDto fromComment(Comment comment) {
        var Dto = new CommentDto();

        Dto.id = comment.getId();
        Dto.text = comment.getText();
        Dto.createdDate = comment.getCreatedDate();
        Dto.project = ProjectDto.fromProject(comment.getProject());
        Dto.user = UserDto.fromUser(comment.getUser());
        Dto.parentComment = CommentDto.fromComment(comment.getParentComment());

        return Dto;
    }


}
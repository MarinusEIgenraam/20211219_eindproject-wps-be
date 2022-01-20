package com.willpowered.eindprojectwpsbe.Comment;


import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.auth.UserDto;
import lombok.var;

import java.time.LocalDateTime;

public class CommentDto {

    public Long id;
    public String text;
    public LocalDateTime startTime;
    public ProjectDto project;
    public UserDto user;
    public CommentDto parentComment;

    public static CommentDto fromComment(Comment comment) {
        var Dto = new CommentDto();

        Dto.id = comment.getId();
        Dto.text = comment.getText();
        Dto.startTime = comment.getStartTime();
        Dto.project = ProjectDto.fromProject(comment.getProject());
        Dto.user = UserDto.fromUser(comment.getUser());
        Dto.parentComment = CommentDto.fromComment(comment.getParentComment());

        return Dto;
    }


}
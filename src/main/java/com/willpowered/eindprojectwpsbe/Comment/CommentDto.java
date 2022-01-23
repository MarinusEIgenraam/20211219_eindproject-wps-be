package com.willpowered.eindprojectwpsbe.Comment;


import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.auth.UserDto;
import lombok.var;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class CommentDto {

    public Long id;
    public String text;
    public LocalDateTime startTime;
    public UserDto user;
    public List<CommentDto> commentList;


    public static CommentDto fromComment(Comment comment) {
        var Dto = new CommentDto();

        Dto.id = comment.getId();
        Dto.text = comment.getText();
        Dto.startTime = comment.getStartTime();
        Dto.user = UserDto.fromUser(comment.getUser());
        Dto.commentList = comment.getCommentList().stream().map(c -> CommentDto.fromComment(c)).collect(Collectors.toList());

        return Dto;
    }


}
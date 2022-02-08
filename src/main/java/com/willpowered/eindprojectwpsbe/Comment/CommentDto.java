package com.willpowered.eindprojectwpsbe.Comment;


import com.willpowered.eindprojectwpsbe.User.UserDto;

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
        var dto = new CommentDto();

        dto.id = comment.getId();
        dto.text = comment.getText();
        dto.startTime = comment.getStartTime();
        dto.user = UserDto.fromUser(comment.getUser());
        if (comment.getCommentList() != null) {
            dto.commentList = comment.getCommentList().stream().map(c -> CommentDto.fromComment(c)).collect(Collectors.toList());
        }

        return dto;
    }


}
package com.willpowered.eindprojectwpsbe.dto.communication.Comment;

import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import lombok.var;

import java.time.Instant;

public class CommentInputDto {

    public Long id;
    public String text;
    public Instant createdDate;
    public Long projectId;
    public String username;
    public Long parentCommentId;

    public Comment toComment() {
        var comment = new Comment();

        comment.setId(id);
        comment.setText(text);
        comment.setCreatedDate(createdDate);

        return comment;
    }
}
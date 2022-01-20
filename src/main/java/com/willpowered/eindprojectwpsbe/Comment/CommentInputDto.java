package com.willpowered.eindprojectwpsbe.Comment;


import com.sun.istack.Nullable;
import lombok.var;

import java.time.Instant;

public class CommentInputDto {

    public Long id;
    public String text;
    public Long projectId;
    public String username;
    public Long parentCommentId;

    public Comment toComment() {
        var comment = new Comment();

        comment.setId(id);
        comment.setText(text);

        return comment;
    }
}
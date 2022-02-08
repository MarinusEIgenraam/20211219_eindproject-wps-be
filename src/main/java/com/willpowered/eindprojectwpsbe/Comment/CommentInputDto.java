package com.willpowered.eindprojectwpsbe.Comment;


public class CommentInputDto {

    public Long id;
    public String text;
    public Long parentProjectId;
    public Long parentBlogId;
    public Long parentCommentId;

    public Comment toComment() {
        var comment = new Comment();

        comment.setId(id);
        comment.setText(text);

        return comment;
    }
}
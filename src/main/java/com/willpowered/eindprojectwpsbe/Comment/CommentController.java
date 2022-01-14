package com.willpowered.eindprojectwpsbe.Comment;


import com.willpowered.eindprojectwpsbe.exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor
@Slf4j
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public CommentDto getComment(@PathVariable("id") Long id) {
        var comment = commentService.getComment(id);
        return CommentDto.fromComment(comment);
    }

    @GetMapping
    public List<CommentDto> getComments(
            @RequestParam(value = "projectId", required = false) Long projectId,
            @RequestParam(value = "parentCommentId", required = false) Long parentCommentId,
            @RequestParam(value = "username", required = false) String username
    ) {
        var dtos = new ArrayList<CommentDto>();

        List<Comment> comments;
        if (projectId != null && parentCommentId == null && username == null) {
            comments = commentService.getCommentsForProject(projectId);
        } else if  (projectId == null && parentCommentId != null && username == null) {
            comments = commentService.getCommentsForParentComment(parentCommentId);
        } else if (projectId == null && parentCommentId == null && username != null) {
            comments = commentService.getCommentsForUser(username);
        } else {
            throw new BadRequestException();
        }

        for (Comment comment : comments) {
            dtos.add(CommentDto.fromComment(comment));
        }

        return dtos;
    }

    @PostMapping
    public CommentDto saveComment(@RequestBody CommentInputDto dto) {
        var comment = commentService.saveComment(dto.projectId, dto.parentCommentId, dto.username, dto.createdDate, dto.text);
        return CommentDto.fromComment(comment);
    }

    @PutMapping("/{id}")
    public CommentDto updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        commentService.updateComment(id, comment);
        return CommentDto.fromComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }
}
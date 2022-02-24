package com.willpowered.eindprojectwpsbe.Comment;


import com.willpowered.eindprojectwpsbe.Exception.BadRequestException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
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
    public Page<CommentDto> getComments(
            @RequestParam(value = "parentProjectId", required = false) Long parentProjectId,
            @RequestParam(value = "parentBlogId", required = false) Long parentBlogId,
            @RequestParam(value = "parentCommentId", required = false) Long parentCommentId,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "page", defaultValue = "0", required = false) int page,
            @RequestParam(value = "size", defaultValue = "10", required = false) int size,
            @RequestParam(value = "sort", defaultValue = "startTime", required = false) String[] sort
    ) {
        var dtos = new ArrayList<CommentDto>();
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort).descending());

        List<Comment> comments;
        if (parentProjectId != null && parentCommentId == null && parentBlogId == null && username == null) {
            comments = commentService.getCommentsForParentProject(parentProjectId, pageable);
        } else if  (parentProjectId == null && parentCommentId != null && parentBlogId == null && username == null) {
            comments = commentService.getCommentsForParentComment(parentCommentId, pageable);
        } else if (parentProjectId == null && parentCommentId == null && parentBlogId != null && username == null) {
            comments = commentService.getCommentsForParentBlog(parentBlogId, pageable);
        }else if (parentProjectId == null && parentCommentId == null && parentBlogId == null && username != null) {
            comments = commentService.getCommentsForUser(username, pageable);
        } else {
            throw new BadRequestException();
        }

        for (Comment comment : comments) {
            dtos.add(CommentDto.fromComment(comment));
        }

        Page<CommentDto> pageOfComments = new PageImpl<>(dtos);

        return pageOfComments;
    }

    @PostMapping
    public CommentDto saveComment(@RequestBody CommentInputDto dto) {
        return CommentDto.fromComment(commentService.saveComment(dto));
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
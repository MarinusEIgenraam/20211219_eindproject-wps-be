package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Comment.CommentDto;
import com.willpowered.eindprojectwpsbe.dto.communication.Comment.CommentInputDto;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.service.communication.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public CommentDto saveComment(@RequestBody CommentInputDto Dto) {
        var comment = commentService.saveComment(Dto.toComment());
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
package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Comment.CommentDTO;
import com.willpowered.eindprojectwpsbe.dto.communication.Comment.CommentInputDTO;
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
    public CommentDTO getComment(@PathVariable("id") Long id) {
        var comment = commentService.getComment(id);
        return CommentDTO.fromComment(comment);
    }

    @PostMapping
    public CommentDTO saveComment(@RequestBody CommentInputDTO dto) {
        var comment = commentService.saveComment(dto.toComment());
        return CommentDTO.fromComment(comment);
    }

    @PutMapping("/{id}")
    public CommentDTO updateComment(@PathVariable Long id, @RequestBody Comment comment) {
        commentService.updateComment(id, comment);
        return CommentDTO.fromComment(comment);
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }
}
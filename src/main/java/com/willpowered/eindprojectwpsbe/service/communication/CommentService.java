package com.willpowered.eindprojectwpsbe.service.communication;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.CommentRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CommentService {

    private static String PROJECT_URL = "";

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;
    @Autowired
    private CommentRepository commentRepository;


    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public Comment getComment(Long id) {
        Optional<Comment> comment = commentRepository.findById(id);

        if(comment.isPresent()) {
            return comment.get();
        } else {
            throw new RecordNotFoundException("Machine does not exist");
        }
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void updateComment(Long id, Comment comment) {
        Optional<Comment> optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);
            commentRepository.save(comment);
        } else {
            throw new RecordNotFoundException("comment does not exist");
        }
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


}

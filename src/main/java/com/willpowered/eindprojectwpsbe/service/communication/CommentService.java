package com.willpowered.eindprojectwpsbe.service.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Comment.CommentDto;
import com.willpowered.eindprojectwpsbe.dto.communication.Comment.CommentInputDto;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.exception.UserNotFoundException;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.AlertRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.CommentRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
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
    @Autowired
    private AlertService alertService;


    public Comment getComment(Long commentId) {
        Optional<Comment> comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new RecordNotFoundException("Comment does not exist");
        }
    }

    public List<Comment> getCommentsForProject(Long projectId) {
        var optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return commentRepository.findAllByProject(project);
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Comment> getCommentsForParentComment(Long parentCommentId) {
        var optionalComment = commentRepository.findById(parentCommentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            return commentRepository.findAllByParentComment(comment);
        } else {
            throw new RecordNotFoundException("Parent comment does not exist");
        }
    }

    public List<Comment> getCommentsForUser(String username) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return commentRepository.findAllByUser(user);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    public Comment saveComment(Long projectId, Long parentCommentId, String username, Instant createdDate, String text) {

        var optionalProject = projectRepository.findById(projectId);
        var optionalParentComment = commentRepository.findById(parentCommentId);
        var optionalUser = userRepository.findById(username);

        if (!optionalProject.isPresent() && !optionalParentComment.isPresent()) {
            throw new RecordNotFoundException("Missing comment connection");
        }

        var project = optionalProject.get();
        var parentComment = optionalParentComment.get();

        var comment = new Comment();
        comment.setProject(project);
        comment.setUser(userAuthenticateService.getCurrentUser());
        comment.setParentComment(parentComment);
        comment.setCreatedDate(createdDate);
        comment.setText(text);


        var optionalParentCommentUser = optionalParentComment.get().getUser();
        var projectName = optionalProject.get().getProjectName();
        var projectUser = optionalProject.get().getProjectOwner();


        if (optionalParentComment.isPresent()) {
            alertService.addAlert("Comment reply by " + username, "Someone replied on your comment", optionalParentCommentUser);
        } else if (!optionalParentComment.isPresent() && optionalProject.isPresent()) {
            alertService.addAlert("Comment on " + projectName, "Someone commented on your project", projectUser);
        }


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

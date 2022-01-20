package com.willpowered.eindprojectwpsbe.Comment;


import com.willpowered.eindprojectwpsbe.Alert.AlertService;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.auth.User;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
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

    public Comment saveComment(Long projectId, Long parentCommentId, String username, String text) {

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

    public Integer calculateComments(Project project) {
        return commentRepository.findByProject(project).size();
    }
}

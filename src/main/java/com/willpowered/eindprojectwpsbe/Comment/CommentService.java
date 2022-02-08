package com.willpowered.eindprojectwpsbe.Comment;


import com.willpowered.eindprojectwpsbe.Alert.AlertService;
import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Blog.BlogRepository;
import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserRepository;
import com.willpowered.eindprojectwpsbe.User.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommentService {


    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private AlertService alertService;
    @Autowired
    UserService userService;

    //////////////////////////////
    //// Create

    public Comment saveComment(CommentInputDto dto) {
        Comment newComment = dto.toComment();
        newComment.setUser(userService.getCurrentUser());

        if (dto.parentBlogId != null && dto.parentProjectId == null && dto.parentCommentId == null) {
            var optionalBlog = blogRepository.findById(dto.parentBlogId);
            if (optionalBlog.isPresent()) {
                newComment.setParentBlog(optionalBlog.get());
                alertService.addAlert("Comment on blog", optionalBlog.get().getBlogOwner());
            }
        } else if (dto.parentBlogId == null && dto.parentProjectId != null && dto.parentCommentId == null) {
            var optionalProject = projectRepository.findById(dto.parentProjectId);
            if (optionalProject.isPresent()) {
                newComment.setParentProject(optionalProject.get());
                alertService.addAlert("Comment on project", optionalProject.get().getProjectOwner());
            }
        } else if (dto.parentBlogId == null && dto.parentProjectId == null && dto.parentCommentId != null) {
            var optionalComment = commentRepository.findById(dto.parentCommentId);
            if (optionalComment.isPresent()) {
                newComment.setParentComment(optionalComment.get());
                alertService.addAlert("Comment on comment", optionalComment.get().getUser());
                Comment savedComment = commentRepository.save(newComment);
                Comment parentComment = optionalComment.get();
                parentComment.getCommentList().add(savedComment);
                commentRepository.save(parentComment);
                newComment = savedComment;
            }
        } else if (dto.parentBlogId == null && dto.parentProjectId == null && dto.parentCommentId == null) {
            throw new RecordNotFoundException("Missing comment parent");
        }
        return commentRepository.save(newComment);
    }

    //////////////////////////////
    //// Read

    public Comment getComment(Long commentId) {
        var comment = commentRepository.findById(commentId);

        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new RecordNotFoundException("Comment does not exist");
        }
    }

    public List<Comment> getCommentsForParentProject(Long projectId, Pageable pageable) {
        var optionalProject = projectRepository.findById(projectId);

        if (optionalProject.isPresent()) {
            Project project = optionalProject.get();
            return commentRepository.findAllByParentProject(project, pageable);
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Comment> getCommentsForParentBlog(Long blogId, Pageable pageable) {
        var optionalBlog = blogRepository.findById(blogId);

        if (optionalBlog.isPresent()) {
            Blog blog = optionalBlog.get();
            return commentRepository.findAllByParentBlog(blog, pageable);
        } else {
            throw new RecordNotFoundException("Project does not exist");
        }
    }

    public List<Comment> getCommentsForParentComment(Long parentCommentId, Pageable pageable) {
        var optionalComment = commentRepository.findById(parentCommentId);
        if (optionalComment.isPresent()) {
            Comment comment = optionalComment.get();
            return commentRepository.findAllByParentComment(comment, pageable);
        } else {
            throw new RecordNotFoundException("Parent comment does not exist");
        }
    }

    public List<Comment> getCommentsForUser(String username, Pageable pageable) {
        var optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return commentRepository.findAllByUser(user, pageable);
        } else {
            throw new RecordNotFoundException("No user found");
        }
    }

    //////////////////////////////
    //// Update

    public void updateComment(Long id, Comment comment) {
        var optionalComment = commentRepository.findById(id);
        if (optionalComment.isPresent()) {
            commentRepository.deleteById(id);
            commentRepository.save(comment);
        } else {
            throw new RecordNotFoundException("comment does not exist");
        }
    }

    //////////////////////////////
    //// Delete

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }

    //////////////////////////////
    //// Helpers

    public Integer calculateComments(Project project) {
        return commentRepository.findAllByParentProject(project).size();
    }
}

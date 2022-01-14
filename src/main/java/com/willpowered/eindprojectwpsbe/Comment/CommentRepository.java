package com.willpowered.eindprojectwpsbe.Comment;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProject(Project project);

    List<Comment> findAllByUser(User user);

    List<Comment> findAllByProject(Project project);

    List<Comment> findAllByParentComment(Comment comment);

}

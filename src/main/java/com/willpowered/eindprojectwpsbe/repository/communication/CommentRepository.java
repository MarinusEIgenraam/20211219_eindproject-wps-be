package com.willpowered.eindprojectwpsbe.repository.communication;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByProject(Project project);

    List<Comment> findAllByUser(User user);

    List<Comment> findAllByProject(Project project);

    List<Comment> findAllByParentComment(Comment comment);

}

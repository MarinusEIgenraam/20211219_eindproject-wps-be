package com.willpowered.eindprojectwpsbe.repository.communication;

import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Collection<Object> findByProject(Project project);
}

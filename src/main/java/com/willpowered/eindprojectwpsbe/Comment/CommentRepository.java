package com.willpowered.eindprojectwpsbe.Comment;

import com.willpowered.eindprojectwpsbe.Blog.Blog;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByUser(User user, Pageable pageable);

    List<Comment> findAllByParentProject(Project project, Pageable pageable);
    List<Comment> findAllByParentProject(Project project);

    List<Comment> findAllByParentBlog(Blog blog, Pageable pageable);

    List<Comment> findAllByParentComment(Comment comment, Pageable pageable);

}

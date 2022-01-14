package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.auth.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findByBlogName(String blogName);

    List<Blog> findAllByBlogOwner(User user);
}

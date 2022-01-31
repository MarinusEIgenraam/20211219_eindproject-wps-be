package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.auth.User;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Optional<Blog> findByBlogName(String blogName);

    Page<Blog> findAllByBlogOwner(User user, Pageable pageable);

    Page<Blog> findAll(Pageable pageable);
}

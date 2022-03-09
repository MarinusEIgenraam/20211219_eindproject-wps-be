package com.willpowered.eindprojectwpsbe.Blog;

import com.willpowered.eindprojectwpsbe.User.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

    Page<Blog> findAllByBlogOwner(User user, Pageable pageable);

    Page<Blog> findAll(Pageable pageable);

}

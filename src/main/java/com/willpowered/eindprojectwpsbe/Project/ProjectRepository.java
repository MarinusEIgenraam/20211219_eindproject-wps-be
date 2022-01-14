package com.willpowered.eindprojectwpsbe.Project;

import com.willpowered.eindprojectwpsbe.Category.Category;
import com.willpowered.eindprojectwpsbe.auth.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findByProjectOwner(User user);

    Optional<Project> findByProjectName(String projectName);


    void findByCategoryId(Long categoryId);

    List<Project> findAllByCategory(Category category);

    List<Project> findAllByProjectOwner(User user);
}

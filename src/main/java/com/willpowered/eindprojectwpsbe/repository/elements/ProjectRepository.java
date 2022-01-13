package com.willpowered.eindprojectwpsbe.repository.elements;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
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

    List<Project> findAllByCollaborators(User user);
}

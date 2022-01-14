package com.willpowered.eindprojectwpsbe.repository.elements;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {


    List<Project> findByProjectOwner(User user);

    Optional<Project> findByProjectName(String projectName);


    @Query(
            "SELECT p FROM Project p WHERE p.publiclyVisible = true OR p.projectOwner = :user OR :user MEMBER OF p.collaborators")
    List<Project> findAllViewableProjects(@Param("user")User user);

    @Query("SELECT p FROM Project p WHERE :user = p.projectOwner AND (p.publiclyVisible = true OR p.projectOwner = :user OR :user MEMBER OF p.collaborators)")
    List<Project> findAllByProjectOwner(@Param("user") User user);

    @Query("SELECT p FROM Project p WHERE :collaborator MEMBER OF p.collaborators AND (:currentuser = p.projectOwner OR :currentuser MEMBER OF p.collaborators  OR p.publiclyVisible = true)")
    List<Project> findAllByCollaborators(@Param("collaborator") User collaborator,
                                         @Param("currentuser")User currentUser);

    @Query(
            "SELECT p FROM Project p WHERE :category = p.category.id AND (p.publiclyVisible = true OR :user = p.projectOwner OR :user MEMBER OF p.collaborators  OR p.publiclyVisible = true)")
    List<Project> findAllByCategory(@Param("category") Long categoryId,
                                                   @Param("user") User user);

}

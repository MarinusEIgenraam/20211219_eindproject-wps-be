package com.willpowered.eindprojectwpsbe.Project;


import com.willpowered.eindprojectwpsbe.User.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query(
            "SELECT p FROM Project p WHERE p.publiclyVisible = true OR p.projectOwner = :user OR :user MEMBER OF p.collaborators")
    List<Project> findAllViewableProjects(@Param("user") User user, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE :user = p.projectOwner AND (p.publiclyVisible = true OR p.projectOwner = :user OR :user MEMBER OF p.collaborators)")
    List<Project> findAllByProjectOwner(@Param("user") User user, Pageable pageable);

    @Query("SELECT p FROM Project p WHERE :collaborator MEMBER OF p.collaborators AND (:currentuser = p.projectOwner OR :currentuser MEMBER OF p.collaborators  OR p.publiclyVisible = true)")
    List<Project> findAllByCollaborators(@Param("collaborator") User collaborator,
                                         @Param("currentuser") User currentUser, Pageable pageable);

    @Query(
            "SELECT p FROM Project p WHERE :category = p.category.id AND (p.publiclyVisible = true OR :user = p.projectOwner OR :user MEMBER OF p.collaborators  OR p.publiclyVisible = true)")
    List<Project> findAllByCategory(@Param("category") Long categoryId,
                                    @Param("user") User user, Pageable pageable);

}

package com.willpowered.eindprojectwpsbe.Vote;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByProjectAndUserOrderByVoteIdDesc(Project project, User currentUser);

    Collection<Object> findByProject(Project project);
}

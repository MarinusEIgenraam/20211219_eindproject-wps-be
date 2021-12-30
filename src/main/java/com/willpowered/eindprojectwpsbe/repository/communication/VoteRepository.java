package com.willpowered.eindprojectwpsbe.repository.communication;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long> {
    Optional<Vote> findTopByProjectAndUserOrderByVoteIdDesc(Project project, User currentUser);

    Collection<Object> findByProject(Project project);
}

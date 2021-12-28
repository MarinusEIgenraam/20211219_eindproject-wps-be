package com.willpowered.eindprojectwpsbe.dto.communication.Vote;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.model.communication.VoteType;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

public class VoteInputDTO {

    public Long voteId;
    public VoteType voteType;
    public Project project;
    public User user;

    public Vote toVote() {
        var vote = new Vote();

        vote.setVoteId(voteId);
        vote.setVoteType(voteType);
        vote.setProject(project);
        vote.setUser(user);

        return vote;
    }
}
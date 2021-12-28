package com.willpowered.eindprojectwpsbe.dto.communication.Vote;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.model.communication.VoteType;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

public class VoteDTO {

    public Long voteId;
    public VoteType voteType;
    public Project project;
    public User user;

    public static VoteDTO fromVote(Vote vote) {
        var dto = new VoteDTO();

        dto.voteId = vote.getVoteId();
        dto.voteType = vote.getVoteType();
        dto.project = vote.getProject();
        dto.user = vote.getUser();

        return dto;
    }


}
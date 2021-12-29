package com.willpowered.eindprojectwpsbe.dto.communication.Vote;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.model.communication.VoteType;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import lombok.var;

public class VoteDto {

    public Long voteId;
    public VoteType voteType;
    public Project project;
    public User user;

    public static VoteDto fromVote(Vote vote) {
        var Dto = new VoteDto();

        Dto.voteId = vote.getVoteId();
        Dto.voteType = vote.getVoteType();
        Dto.project = vote.getProject();
        Dto.user = vote.getUser();

        return Dto;
    }


}
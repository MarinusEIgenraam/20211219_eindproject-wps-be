package com.willpowered.eindprojectwpsbe.Vote;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.var;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteInputDto {

    public Long voteId;
    public VoteType voteType;
    public Long projectId;
    public String username;

    public Vote toVote() {
        var vote = new Vote();

        vote.setVoteId(voteId);
        vote.setVoteType(voteType);

        return vote;
    }
}
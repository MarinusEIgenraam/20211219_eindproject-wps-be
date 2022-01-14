package com.willpowered.eindprojectwpsbe.Vote;

import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.auth.User.UserDto;
import lombok.var;

public class VoteDto {

    public Long voteId;
    public VoteType voteType;
    public ProjectDto project;
    public UserDto user;

    public static VoteDto fromVote(Vote vote) {
        var Dto = new VoteDto();

        Dto.voteId = vote.getVoteId();
        Dto.voteType = vote.getVoteType();
        Dto.project = ProjectDto.fromProject(vote.getProject());
        Dto.user = UserDto.fromUser(vote.getUser());

        return Dto;
    }


}
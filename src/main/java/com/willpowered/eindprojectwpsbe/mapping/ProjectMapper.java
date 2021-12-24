package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectResponse;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.model.communication.VoteType;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.repository.communication.CommentRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.VoteRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static com.willpowered.eindprojectwpsbe.model.communication.VoteType.DOWNVOTE;
import static com.willpowered.eindprojectwpsbe.model.communication.VoteType.UPVOTE;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;

    @Mapping(target = "startTime", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "projectRequest.description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "voteCount", constant = "0")
    @Mapping(target = "projectOwner", source = "user")
    public abstract Project map(ProjectRequest projectRequest, Category category, User user);

    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "userName", source = "projectOwner.username")
    @Mapping(target = "commentCount", expression = "java(commentCount(project))")
    @Mapping(target = "upVote", expression = "java(isProjectUpVoted(project))")
    @Mapping(target = "downVote", expression = "java(isProjectDownVoted(project))")
    public abstract ProjectResponse mapToDto(Project project);

    Integer commentCount(Project project) {
        return commentRepository.findByProject(project).size();
    }

    boolean isProjectUpVoted(Project project) {
        return checkVoteType(project, UPVOTE);
    }

    boolean isProjectDownVoted(Project project) {
        return checkVoteType(project, DOWNVOTE);
    }

    private boolean checkVoteType(Project project, VoteType voteType) {
        if (userAuthenticateService.isLoggedIn()) {
            Optional<Vote> voteForProjectByUser =
                    voteRepository.findTopByProjectAndUserOrderByVoteIdDesc(project,
                            userAuthenticateService.getCurrentUser());
            return voteForProjectByUser.filter(vote -> vote.getVoteType().equals(voteType))
                    .isPresent();
        }
        return false;
    }
}

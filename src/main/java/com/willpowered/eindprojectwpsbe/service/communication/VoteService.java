package com.willpowered.eindprojectwpsbe.service.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.VoteDto;
import com.willpowered.eindprojectwpsbe.exception.BadRequestException;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.repository.communication.VoteRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.willpowered.eindprojectwpsbe.model.communication.VoteType.UPVOTE;

@Service
@AllArgsConstructor
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;

    @Transactional
    public void vote(VoteDto voteDto) {

        Project project = projectRepository.findById(voteDto.getProjectId())
                .orElseThrow(() -> new RecordNotFoundException("Project Not Found with ID - " + voteDto.getProjectId()));
        Optional<Vote> voteByProjectAndUser = voteRepository.findTopByProjectAndUserOrderByVoteIdDesc(project, userAuthenticateService.getCurrentUser());

        if (voteByProjectAndUser.isPresent() && voteByProjectAndUser.get().getVoteType().equals(voteDto.getVoteType())) {
            throw new BadRequestException("You have already " + voteDto.getVoteType() + "'d for this project");
        }

        if (UPVOTE.equals(voteDto.getVoteType())) {
            project.setVoteCount(project.getVoteCount() + 1);
        } else {
            project.setVoteCount(project.getVoteCount() - 1);
        }

        voteRepository.save(mapToVote(voteDto, project));
        projectRepository.save(project);
    }

    private Vote mapToVote(VoteDto voteDto, Project project) {
        return Vote.builder()
                .voteType(voteDto.getVoteType())
                .project(project)
                .user(userAuthenticateService.getCurrentUser())
                .build();
    }
}

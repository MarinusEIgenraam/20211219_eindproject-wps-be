package com.willpowered.eindprojectwpsbe.Vote;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.auth.UserAuthenticateService;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.exception.WillpoweredException;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.willpowered.eindprojectwpsbe.Vote.VoteType.UPVOTE;


@Service
@AllArgsConstructor
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;


    public void vote(VoteInputDto voteInputDto) {

        Project project = projectRepository.findById(voteInputDto.getProjectId())
                .orElseThrow(() -> new RecordNotFoundException("No project with ID - " + voteInputDto.getProjectId() + " was found"));
        Optional<Vote> voteByProjectAndUser = voteRepository.findTopByProjectAndUserOrderByVoteIdDesc(project, userAuthenticateService.getCurrentUser());

        if (voteByProjectAndUser.isPresent() && voteByProjectAndUser.get().getVoteType().equals(voteInputDto.getVoteType())) {
            throw new WillpoweredException("You have already " + voteInputDto.getVoteType() + "'d this project");
        }

        if (UPVOTE.equals(voteInputDto.getVoteType())) {
            project.setVoteCount(project.getVoteCount() + 1);
        } else {
            project.setVoteCount(project.getVoteCount() - 1);
        }

        voteRepository.save(saveToVote(voteInputDto, project));
        projectRepository.save(project);
    }

    private Vote saveToVote(VoteInputDto voteInputDto, Project project) {
        var vote = new Vote();

        vote.setVoteType(voteInputDto.getVoteType());
        vote.setProject(project);
        vote.setUser(userAuthenticateService.getCurrentUser());

        return voteRepository.save(vote);
    }

    public Vote saveVote(Vote vote) {
        return voteRepository.save(vote);
    }

    public void updateVote(Long id, Vote vote) {
        Optional<Vote> optionalVote = voteRepository.findById(id);
        if (optionalVote.isPresent()) {
            voteRepository.deleteById(id);
            voteRepository.save(vote);
        } else {
            throw new RecordNotFoundException("vote does not exist");
        }
    }

    public void deleteVote(Long id) {
        voteRepository.deleteById(id);
    }

}
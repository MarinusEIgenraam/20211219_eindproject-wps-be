package com.willpowered.eindprojectwpsbe.service.communication;

import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.repository.communication.VoteRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;


    public List<Vote> getVotes() {
        return voteRepository.findAll();
    }

    public Vote getVote(Long id) {
        Optional<Vote> vote = voteRepository.findById(id);

        if(vote.isPresent()) {
            return vote.get();
        } else {
            throw new RecordNotFoundException("Machine does not exist");
        }
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

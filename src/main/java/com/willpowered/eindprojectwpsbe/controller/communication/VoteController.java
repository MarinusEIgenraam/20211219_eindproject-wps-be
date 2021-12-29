package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Vote.VoteDto;
import com.willpowered.eindprojectwpsbe.dto.communication.Vote.VoteInputDto;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.service.communication.VoteService;
import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public VoteDto saveVote(@RequestBody VoteInputDto voteInputDto) {
        var vote = voteService.saveVote(voteInputDto.toVote());
        return VoteDto.fromVote(vote);
    }

    @PutMapping("/{id}")
    public VoteDto updateVote(@PathVariable Long id, @RequestBody Vote vote) {
        voteService.updateVote(id, vote);
        return VoteDto.fromVote(vote);
    }

    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable("id") Long id) {
        voteService.deleteVote(id);
    }
}
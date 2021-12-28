package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Vote.VoteDTO;
import com.willpowered.eindprojectwpsbe.dto.communication.Vote.VoteInputDTO;
import com.willpowered.eindprojectwpsbe.model.communication.Vote;
import com.willpowered.eindprojectwpsbe.service.communication.VoteService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
@Slf4j
public class VoteController {

    @Autowired
    private VoteService voteService;

    @GetMapping("/{id}")
    public VoteDTO getVote(@PathVariable("id") Long id) {
        var vote = voteService.getVote(id);
        return VoteDTO.fromVote(vote);
    }

    @PostMapping
    public VoteDTO saveVote(@RequestBody VoteInputDTO dto) {
        var vote = voteService.saveVote(dto.toVote());
        return VoteDTO.fromVote(vote);
    }

    @PutMapping("/{id}")
    public VoteDTO updateVote(@PathVariable Long id, @RequestBody Vote vote) {
        voteService.updateVote(id, vote);
        return VoteDTO.fromVote(vote);
    }

    @DeleteMapping("/{id}")
    public void deleteVote(@PathVariable("id") Long id) {
        voteService.deleteVote(id);
    }
}
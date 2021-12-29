package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Vote.VoteDto;
import com.willpowered.eindprojectwpsbe.dto.communication.Vote.VoteInputDto;
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
    public VoteDto getVote(@PathVariable("id") Long id) {
        var vote = voteService.getVote(id);
        return VoteDto.fromVote(vote);
    }

    @PostMapping
    public VoteDto saveVote(@RequestBody VoteInputDto Dto) {
        var vote = voteService.saveVote(Dto.toVote());
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
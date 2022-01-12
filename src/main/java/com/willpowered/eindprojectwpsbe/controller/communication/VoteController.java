package com.willpowered.eindprojectwpsbe.controller.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.Vote.VoteInputDto;
import com.willpowered.eindprojectwpsbe.service.communication.VoteService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/votes")
@AllArgsConstructor
public class VoteController {

    @Autowired
    private VoteService voteService;

    @PostMapping
    public ResponseEntity<Void> vote(@RequestBody VoteInputDto voteInputDto) {
        voteService.vote(voteInputDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
package com.willpowered.eindprojectwpsbe.dto.communication;

import com.willpowered.eindprojectwpsbe.model.communication.VoteType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoteDto {

    private VoteType voteType;
    private Long projectId;
}

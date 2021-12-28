package com.willpowered.eindprojectwpsbe.dto.communication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {

    private Long id;
    private Long projectId;
    private Instant createdDate;
    private String text;
    private String userName;
}
package com.willpowered.eindprojectwpsbe.dto.elements.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private Long id;
    private String name;
    private String description;
    private String url;
    private String userName;
    private String categoryName;
    private Integer voteCount;
    private Integer commentCount;
    private boolean isRunning;
    private Instant startTime;
    private Instant endTime;

}

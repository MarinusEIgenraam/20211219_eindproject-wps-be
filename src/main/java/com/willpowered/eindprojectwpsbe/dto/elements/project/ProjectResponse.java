package com.willpowered.eindprojectwpsbe.dto.elements.project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectResponse {

    private Long id;
    private String name;
    private String url;
    private String description;
    private String userName;
    private String categoryName;
    private Instant startTime;
    private Instant endTime;
    private Integer commentCount;
    private Integer voteCount;
    private boolean upVote;
    private boolean downVote;
    private List<ProjectResponse> projectTaskList;

}

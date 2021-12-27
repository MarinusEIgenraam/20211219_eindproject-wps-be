package com.willpowered.eindprojectwpsbe.dto.elements.project;

import com.willpowered.eindprojectwpsbe.model.elements.Project;
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
    private String description;
    private String url;
    private String userName;
    private String categoryName;
    private Integer voteCount;
    private Integer commentCount;
    private Instant startTime;
    private Instant endTime;
    private boolean upVote;
    private boolean downVote;
    private List<ProjectResponse> projectTaskList;

}

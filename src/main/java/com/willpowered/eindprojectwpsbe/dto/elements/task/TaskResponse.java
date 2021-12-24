package com.willpowered.eindprojectwpsbe.dto.elements.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {

    private Long id;
    private String taskName;
    private String description;
    private Instant startTime;
    private Instant endTime;
    private String userName;
    private boolean isRunning;
    private Long parentTaskName;
    private Long parentProjectName;

}

package com.willpowered.eindprojectwpsbe.dto.elements.task;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.model.elements.Task;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequest {

    private Long taskId;
    private String taskName;
    private String description;
    private String parentTaskName;
    private String parentProjectName;
    private Instant endTime;

}

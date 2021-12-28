package com.willpowered.eindprojectwpsbe.dto.elements.project;

import com.willpowered.eindprojectwpsbe.model.auth.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectRequest {

    private Long projectId;
    private String projectName;
    private String url;
    private String description;
    private String categoryName;

}

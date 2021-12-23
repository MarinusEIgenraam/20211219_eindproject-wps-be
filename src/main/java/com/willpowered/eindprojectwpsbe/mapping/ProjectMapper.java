package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectRequest;
import com.willpowered.eindprojectwpsbe.dto.elements.project.ProjectResponse;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.elements.Category;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.CommentRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.VoteRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.CategoryRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.TaskRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class ProjectMapper {

    @Autowired
    private CommentRepository commentRepository;

    @Mapping(target = "startTime", expression = "java(java.time.Instant.now())")
    @Mapping(target = "description", source = "projectRequest.description")
    @Mapping(target = "category", source = "category")
    @Mapping(target = "voteCount", constant = "0")
    public abstract Project map(ProjectRequest projectRequest, Category category, User user);

    @Mapping(target = "id", source = "projectId")
    @Mapping(target = "categoryName", source = "category.name")
    @Mapping(target = "userName", source = "user.name")
    @Mapping(target = "commentCount", expression = "java(commentCount(post))")
    @Mapping(target = "isRunning", source = "isRunning")
    public abstract ProjectResponse mapToDto(Project project);

    Integer commentCount(Project project) {
        return commentRepository.findByProject(project).size();
    }

}

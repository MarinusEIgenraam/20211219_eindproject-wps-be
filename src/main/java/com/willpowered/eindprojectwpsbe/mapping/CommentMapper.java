package com.willpowered.eindprojectwpsbe.mapping;

import com.willpowered.eindprojectwpsbe.dto.communication.CommentDTO;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "text", source = "commentDTO.text")
    @Mapping(target = "createdDate", expression = "java(java.time.Instant.now())")
    @Mapping(target = "post", source = "post")
    Comment map(CommentDTO commentDTO, Project project, User user);

    @Mapping(target = "id", expression = "java(comment.getProject().getProjectId())")
    @Mapping(target = "userName", expression = "java(comment.getUser().getUserName())")
    CommentDTO mapToDto(Comment comment);

}

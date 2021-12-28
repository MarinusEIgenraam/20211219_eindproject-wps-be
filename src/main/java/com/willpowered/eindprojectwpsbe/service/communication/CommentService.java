package com.willpowered.eindprojectwpsbe.service.communication;

import com.willpowered.eindprojectwpsbe.dto.communication.CommentDto;
import com.willpowered.eindprojectwpsbe.exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.mapping.CommentMapper;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Comment;
import com.willpowered.eindprojectwpsbe.model.elements.Project;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import com.willpowered.eindprojectwpsbe.repository.communication.CommentRepository;
import com.willpowered.eindprojectwpsbe.repository.elements.ProjectRepository;
import com.willpowered.eindprojectwpsbe.service.auth.UserAuthenticateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class CommentService {

    private static String PROJECT_URL = "";

    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserAuthenticateService userAuthenticateService;
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentRepository commentRepository;


    public void save(CommentDto commentsDto) {
        Project project = projectRepository.findById(commentsDto.getProjectId())
                                  .orElseThrow(() -> new RecordNotFoundException(commentsDto.getProjectId().toString()));
        Comment comment = commentMapper.map(commentsDto, project, userAuthenticateService.getCurrentUser());
        commentRepository.save(comment);

    }

    public List<CommentDto> getAllCommentsForProject(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new RecordNotFoundException(projectId.toString()));
        return commentRepository.findByProject(project).stream().map(commentMapper::mapToDto).collect(toList());
    }

    public List<CommentDto> getAllCommentsForUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new RecordNotFoundException(username));
        return commentRepository.findAllByUser(user).stream().map(commentMapper::mapToDto).collect(toList());
    }
}

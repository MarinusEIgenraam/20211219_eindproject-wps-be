package com.willpowered.eindprojectwpsbe.Vote;

import com.willpowered.eindprojectwpsbe.Exception.RecordNotFoundException;
import com.willpowered.eindprojectwpsbe.Exception.WillpoweredException;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectRepository;
import com.willpowered.eindprojectwpsbe.User.User;
import com.willpowered.eindprojectwpsbe.User.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @Mock
    ProjectRepository projectRepository;
    @Mock
    VoteRepository voteRepository;
    @Mock
    UserService userService;

    @InjectMocks
    VoteService voteService;

    @Captor
    ArgumentCaptor<Vote> voteCaptor;

    @Mock
    Project project;
    @Mock
    VoteInputDto voteInputDto;
    @Mock
    User user;

    VoteInputDto upVoteInputDto;
    VoteInputDto downVoteInputDto;
    Vote upVote;
    Vote downVote;

    @BeforeEach
    void setUp() {
        downVoteInputDto = VoteInputDto.builder()
                .projectId(1L)
                .username("userName")
                .voteType(VoteType.DOWNVOTE)
                .build();
        upVoteInputDto = VoteInputDto.builder()
                .projectId(1L)
                .username("nameUser")
                .voteType(VoteType.UPVOTE)
                .build();
        downVote = Vote.builder()
                .voteId(1L)
                .voteType(VoteType.DOWNVOTE)
                .project(project)
                .user(user)
                .build();
        upVote = Vote.builder()
                .voteId(2L)
                .voteType(VoteType.UPVOTE)
                .project(project)
                .user(user)
                .build();
    }

    //////////////////////////////
    //// Create

    @Test
    void upVote() {
        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(project));
        when(userService.getCurrentUser()).thenReturn(user);
        when(voteRepository.findTopByProjectAndUserOrderByVoteIdDesc(project, user)).thenReturn(Optional.empty());
        when(voteRepository.save(any())).thenReturn(upVote);

        voteService.vote(upVoteInputDto);

        verify(projectRepository, times(1)).findById(1L);
        verify(voteRepository, times(1)).findTopByProjectAndUserOrderByVoteIdDesc(project, user);
        verify(voteRepository, times(2)).save(any());
        verify(userService, times(2)).getCurrentUser();
    }

    @Test
    void downVote() {
        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(project));
        when(userService.getCurrentUser()).thenReturn(user);
        when(voteRepository.findTopByProjectAndUserOrderByVoteIdDesc(project, user)).thenReturn(Optional.empty());
        when(voteRepository.save(any())).thenReturn(downVote);

        voteService.vote(downVoteInputDto);

        verify(projectRepository, times(1)).findById(1L);
        verify(voteRepository, times(1)).findTopByProjectAndUserOrderByVoteIdDesc(project, user);
        verify(voteRepository, times(2)).save(any());
        verify(userService, times(2)).getCurrentUser();
    }

    @Test
    public void getWillpoweredExceptionTest() {
        when(projectRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(project));
        when(userService.getCurrentUser()).thenReturn(user);
        when(voteRepository.findTopByProjectAndUserOrderByVoteIdDesc(project, user)).thenReturn(Optional.ofNullable(downVote));


        assertThrows(WillpoweredException.class, () ->
                voteService.vote(downVoteInputDto));
    }

    @Test
    void saveVote() {
        when(userService.getCurrentUser()).thenReturn(user);
        when(voteInputDto.toVote()).thenReturn(upVote);
        when(voteRepository.save(any())).thenReturn(upVote);

        Vote vote = voteService.saveToVote(voteInputDto, project);

        verify(voteRepository, times(1)).save(any());
        assertEquals(upVote, vote);
    }

    @Test
    void updateVote() {
        when(voteRepository.findById(1L)).thenReturn(Optional.ofNullable(upVote));

        voteService.updateVote(1L, upVote);

        verify(voteRepository, times(1)).findById(1L);
        assertThrows(RecordNotFoundException.class, () ->
                voteService.updateVote(3L, upVote));
    }


    //////////////////////////////
    //// Delete

    @Test
    void deleteVote() {
        when(voteRepository.findById(1L)).thenReturn(Optional.ofNullable(upVote));

        voteService.deleteVote(1L);

        verify(voteRepository, times(1)).findById(1L);
        verify(voteRepository, times(1)).deleteById(1L);
    }
}
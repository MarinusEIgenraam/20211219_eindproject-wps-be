package com.willpowered.eindprojectwpsbe.Vote;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "projectVotes")
public class Vote {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long voteId;

    private VoteType voteType;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userName", referencedColumnName = "username")
    private User user;

}


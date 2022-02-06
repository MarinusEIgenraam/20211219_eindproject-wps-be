package com.willpowered.eindprojectwpsbe.Vote;

import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.auth.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;


@Getter
@Setter
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
    @ManyToOne(fetch = LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    @ManyToOne(fetch = LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "userName", referencedColumnName = "username")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

}


package com.willpowered.eindprojectwpsbe.Portal;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.Alert.Alert;
import com.willpowered.eindprojectwpsbe.ProfileImage.ProfileImage;
import com.willpowered.eindprojectwpsbe.User.User;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "portals")
public class Portal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Nullable
    @OneToMany
    @JsonManagedReference("portal_alerts")
    private List<Alert> alertList;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "portalOwner", referencedColumnName = "username")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User portalOwner;

    @OneToOne(fetch = LAZY)
    @JoinColumn(name = "profileImage", referencedColumnName = "id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ProfileImage profileImage;
}

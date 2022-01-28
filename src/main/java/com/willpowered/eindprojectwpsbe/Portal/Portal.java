package com.willpowered.eindprojectwpsbe.Portal;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.willpowered.eindprojectwpsbe.Alert.Alert;
import com.willpowered.eindprojectwpsbe.SettingSchema.SettingSchema;
import com.willpowered.eindprojectwpsbe.auth.User;
import lombok.*;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portals")
public class Portal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "portal_alerts",
            joinColumns = @JoinColumn(name = "portal_id"),
            inverseJoinColumns = @JoinColumn(name = "alert_id"))
    @JsonBackReference("portal_alerts")
    private List<Alert> alertList;

    @OneToOne(fetch = LAZY)
    private User user;


}

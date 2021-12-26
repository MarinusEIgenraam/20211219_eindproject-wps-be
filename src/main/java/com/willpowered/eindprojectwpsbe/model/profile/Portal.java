package com.willpowered.eindprojectwpsbe.model.profile;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

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
            name = "portals_alerts",
            joinColumns = @JoinColumn(name = "portalId"),
            inverseJoinColumns = @JoinColumn(name = "alertId"))
    private List<Alert> alertList;

    @OneToOne(fetch = LAZY)
    private User user;

    @OneToOne(fetch = LAZY)
    private SettingSchema settingsSchema;

}

package com.willpowered.eindprojectwpsbe.model.profile;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.model.communication.Alert;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
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

    @OneToOne(fetch = LAZY)
    private SettingSchema settingsSchema;

}

package com.willpowered.eindprojectwpsbe.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(fetch = LAZY, cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Alert> alerts;

    @OneToOne(fetch = LAZY)
    private User user;

    @OneToOne(fetch = LAZY)
    private SettingSchema settingsSchema;

}

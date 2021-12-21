package com.willpowered.eindprojectwpsbe.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "settingSchemas")
public class SettingSchema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private boolean commentAlert = true;
    private boolean taskAlert = true;

}

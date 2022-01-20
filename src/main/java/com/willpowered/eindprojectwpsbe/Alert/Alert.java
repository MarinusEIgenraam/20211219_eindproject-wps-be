package com.willpowered.eindprojectwpsbe.Alert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alerts")
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("portal_alerts")
    private Portal portal;
}

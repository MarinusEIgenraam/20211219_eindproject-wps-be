package com.willpowered.eindprojectwpsbe.Alert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

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
    private LocalDate createdAt;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference("portal_alerts")
    private Portal portal;

    public Alert(String title, String text) {
        this.title = title;
        this.text = text;
    }
}

package com.willpowered.eindprojectwpsbe.Alert;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.willpowered.eindprojectwpsbe.Portal.Portal;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "alerts")
@Builder
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private LocalDate createdAt;

    @ManyToOne
    @JoinTable(
            name = "portal_alerts",
            joinColumns = @JoinColumn(name = "alert_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "portal_id", referencedColumnName = "id"))
    @JsonBackReference("portal_alerts")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Portal portal;

}

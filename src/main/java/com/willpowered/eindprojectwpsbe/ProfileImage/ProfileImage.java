package com.willpowered.eindprojectwpsbe.ProfileImage;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.FetchType.LAZY;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "profile_images")
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fileName;

    private String title;

    private String mediaType;

    private String location;

    private Date uploadedTimestamp;

    @OneToOne(fetch = LAZY)
    private Portal portal;

    private String uploadedBy;
}

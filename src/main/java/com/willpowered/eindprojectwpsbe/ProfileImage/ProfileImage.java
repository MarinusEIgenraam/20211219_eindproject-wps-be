package com.willpowered.eindprojectwpsbe.ProfileImage;

import com.willpowered.eindprojectwpsbe.Portal.Portal;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "profile_images")
public class ProfileImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fileName = "profileImage.jpg";

    @Nullable
    private String title = "profileImage";

    @Nullable
    private String mediaType;

    private String location = "profileImage.jpg";

    private Date uploadedTimestamp = new Date();

    @OneToOne(mappedBy = "profileImage")
    private Portal portal;

    @Nullable
    private String uploadedBy;

    @Transient
    public String getPhotosImagePath() {
        return "/uploads/" + id + "/" + location;

    }
}

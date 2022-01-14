package com.willpowered.eindprojectwpsbe.dto.profile.ProfileImage;

import com.willpowered.eindprojectwpsbe.model.profile.ProfileImage;
import lombok.var;
import org.springframework.web.multipart.MultipartFile;

public class ProfileImageInputDto {

    public String title;
    public MultipartFile file;

    public ProfileImage toProfileImage() {
        var profileImage = new ProfileImage();

        profileImage.setTitle(title);

        return profileImage;
    }
}

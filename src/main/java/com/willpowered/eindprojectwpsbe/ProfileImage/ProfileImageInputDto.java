package com.willpowered.eindprojectwpsbe.ProfileImage;


import lombok.AllArgsConstructor;
import lombok.var;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
public class ProfileImageInputDto {

    public String title;
    public MultipartFile file;

    public ProfileImage toProfileImage() {
        var profileImage = new ProfileImage();

        profileImage.setTitle(title);

        return profileImage;
    }
}

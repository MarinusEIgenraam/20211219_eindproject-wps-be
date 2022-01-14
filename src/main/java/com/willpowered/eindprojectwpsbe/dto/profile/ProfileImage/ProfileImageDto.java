package com.willpowered.eindprojectwpsbe.dto.profile.ProfileImage;

import com.willpowered.eindprojectwpsbe.model.profile.ProfileImage;
import lombok.var;

public class ProfileImageDto {

    public String title;
    public String fileName;
    public String mediaType;
    public String downloadUri;

    public static ProfileImageDto fromProfileImage(ProfileImage profileImage) {
        var dto = new ProfileImageDto();

        dto.title = profileImage.getTitle();
        dto.fileName = profileImage.getFileName();
        dto.mediaType = profileImage.getMediaType();

        return dto;
    }
}

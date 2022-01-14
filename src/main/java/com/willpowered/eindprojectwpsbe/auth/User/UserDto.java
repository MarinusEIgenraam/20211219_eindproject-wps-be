package com.willpowered.eindprojectwpsbe.auth.User;

import com.willpowered.eindprojectwpsbe.auth.Models.User;
import lombok.var;

public class UserDto {

    public String username;

    public static UserDto fromUser(User user) {
        var dto = new UserDto();

        dto.username = user.getUsername();

        return dto;
    }
}
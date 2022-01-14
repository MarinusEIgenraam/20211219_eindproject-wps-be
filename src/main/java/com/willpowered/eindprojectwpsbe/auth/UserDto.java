package com.willpowered.eindprojectwpsbe.auth;

import lombok.var;

public class UserDto {

    public String username;
    public String email;

    public static UserDto fromUser(User user) {
        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.email = user.getEmail();

        return dto;
    }
}
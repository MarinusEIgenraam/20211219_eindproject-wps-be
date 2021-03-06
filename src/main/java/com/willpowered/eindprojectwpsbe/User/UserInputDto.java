package com.willpowered.eindprojectwpsbe.User;

import lombok.Builder;

import java.util.Set;

@Builder
public class UserInputDto {

    public String username;
    public String password;
    public String email;
    public Set<String> authorities;

    public User toUser() {
        var user = new User();

        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);

        return user;
    }
}

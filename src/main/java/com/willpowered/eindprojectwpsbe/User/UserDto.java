package com.willpowered.eindprojectwpsbe.User;

import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Project.Project;

import java.util.Set;

public class UserDto {

    public String username;
    public String email;
    public Set<Authority> authorities;
    public Set<Project> projects;

    public static UserDto fromUser(User user) {
        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();
        dto.projects = user.getProjects();

        return dto;
    }
}
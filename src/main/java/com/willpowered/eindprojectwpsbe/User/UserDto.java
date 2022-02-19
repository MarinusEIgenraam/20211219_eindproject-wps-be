package com.willpowered.eindprojectwpsbe.User;

import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Project.Project;
import com.willpowered.eindprojectwpsbe.Project.ProjectDto;
import com.willpowered.eindprojectwpsbe.Task.TaskDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserDto {

    public String username;
    public String email;
    public Set<Authority> authorities;

    public static UserDto fromUser(User user) {
        var dto = new UserDto();

        dto.username = user.getUsername();
        dto.email = user.getEmail();
        dto.authorities = user.getAuthorities();

        return dto;
    }
}
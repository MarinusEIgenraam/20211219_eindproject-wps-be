package com.willpowered.eindprojectwpsbe.auth;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class AuthenticationRequestDto {

    @NotEmpty
    private String username;

    @Size(min = 8)
    private String password;

    @NotEmpty
    private String email;


    public AuthenticationRequestDto() {
    }

    public AuthenticationRequestDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

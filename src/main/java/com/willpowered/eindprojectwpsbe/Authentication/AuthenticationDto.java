package com.willpowered.eindprojectwpsbe.Authentication;

public class AuthenticationDto {

    private final String jwt;

    public AuthenticationDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}

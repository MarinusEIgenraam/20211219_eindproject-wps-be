package com.willpowered.eindprojectwpsbe.auth;

public class AuthenticationResponseDto {

    private final String jwt;

    public AuthenticationResponseDto(String jwt) {
        this.jwt = jwt;
    }

    public String getJwt() {
        return jwt;
    }

}

package com.willpowered.eindprojectwpsbe.controller;

import com.willpowered.eindprojectwpsbe.dto.AuthenticationResponse;
import com.willpowered.eindprojectwpsbe.dto.LoginRequest;
import com.willpowered.eindprojectwpsbe.dto.RefreshTokenRequest;
import com.willpowered.eindprojectwpsbe.dto.RegisterRequest;
import com.willpowered.eindprojectwpsbe.service.AuthenticateService;
import com.willpowered.eindprojectwpsbe.service.RefreshTokenService;
import com.willpowered.eindprojectwpsbe.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticateService authenticateService;
    private final RefreshTokenService refreshTokenService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        userService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful",
                OK);
    }

    @PostMapping("/login")
    public AuthenticationResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticateService.login(loginRequest);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.deleteRefreshToken(refreshTokenRequest.getRefreshToken());
        return ResponseEntity.status(OK).body("Refresh Token Deleted Successfully!!");
    }

}

package com.willpowered.eindprojectwpsbe.auth;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/authenticate")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<Object> createUser(@RequestBody AuthenticationRequestDto user) {
        String newUsername = userService.registerUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(newUsername)
                .toUri();

        return ResponseEntity.created(location).build();
    }
    

}

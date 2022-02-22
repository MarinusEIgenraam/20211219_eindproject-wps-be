package com.willpowered.eindprojectwpsbe.Authentication;

import com.willpowered.eindprojectwpsbe.User.UserInputDto;
import com.willpowered.eindprojectwpsbe.User.UserService;
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
public class AuthenticationController {

    @Autowired
    AuthenticationService authenticationService;
    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationInputDto authenticationInputDto) {

        AuthenticationDto authenticationDto = authenticationService.authenticateUser(authenticationInputDto);

        return ResponseEntity.ok(authenticationDto);
    }


    @PostMapping(value = "/register")
    public ResponseEntity<Object> createUser(@RequestBody AuthenticationInputDto user) {
        String newUsername = userService.registerUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }
}

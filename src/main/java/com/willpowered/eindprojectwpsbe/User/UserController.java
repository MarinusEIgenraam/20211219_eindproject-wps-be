package com.willpowered.eindprojectwpsbe.User;

import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{username}")
    public UserDto getUser(@PathVariable("username") String username) {
        var user = userService.getUser(username);
        UserDto dto = UserDto.fromUser(user);
        return dto;
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createUser(@RequestBody UserInputDto user) {
        String newUsername = userService.createUser(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{username}")
                .buildAndExpand(newUsername).toUri();

        return ResponseEntity.created(location).build();
    }

    @GetMapping
    public Page<UserDto> getUsers(
                    @RequestParam(value = "authority", required = false) String authority,
                    @RequestParam(value = "page", defaultValue = "0") int page,
                    @RequestParam(value = "size", defaultValue = "10") int size,
                    @RequestParam(value = "sort", defaultValue = "authority,username") String[] sort
    ){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        List<User> users;
        Page<UserDto> userDtoPage;
        var dtos = new ArrayList<UserDto>();

        if (authority == null) {
            users = userService.getUsers();
        } else {
            users = userService.getUsersByRole(authority, pageable);
        }

        for (User user : users) {
            dtos.add(UserDto.fromUser(user));
        }
        Page<UserDto> pageOfUsers = new PageImpl<>(dtos);

        return pageOfUsers;
    }

    @PutMapping(value = "/{username}")
    public ResponseEntity<Object> updateUser(@PathVariable("username") String username, @RequestBody User user) {
        userService.updateUser(username, user);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/password")
    public ResponseEntity<Object> updatePassword(@RequestBody PasswordInputDto passwordInputDto) {
        userService.setPassword(passwordInputDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/{username}")
    public ResponseEntity<Object> deleteUser(@PathVariable("username") String username) {
        userService.deleteUser(username);
        return ResponseEntity.noContent().build();
    }

}

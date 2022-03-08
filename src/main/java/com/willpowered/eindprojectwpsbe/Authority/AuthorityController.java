package com.willpowered.eindprojectwpsbe.Authority;

import com.willpowered.eindprojectwpsbe.Exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class AuthorityController {

    @Autowired
    private AuthorityService authorityService;

    //////////////////////////////
    //// Create

    @PostMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> addUserAuthority(@PathVariable("username") String username, @RequestBody Map<String, Object> fields) {
        try {
            String authorityName = (String) fields.get("authority");
            authorityService.addAuthority(username, authorityName);
            return ResponseEntity.noContent().build();
        } catch (Exception ex) {
            throw new BadRequestException();
        }
    }

    //////////////////////////////
    //// Read

    @GetMapping(value = "/{username}/authorities")
    public ResponseEntity<Object> getUserAuthorities(@PathVariable("username") String username) {
        return ResponseEntity.ok().body(authorityService.getAuthorities(username));
    }

    //////////////////////////////
    //// Delete

    @DeleteMapping(value = "/{username}/authorities/{authority}")
    public ResponseEntity<Object> deleteUserAuthority(@PathVariable("username") String username, @PathVariable("authority") String authority) {
        authorityService.removeAuthority(username, authority);
        return ResponseEntity.noContent().build();
    }
}

package com.willpowered.eindprojectwpsbe.User;

import com.willpowered.eindprojectwpsbe.Authentication.AuthenticationService;
import com.willpowered.eindprojectwpsbe.Authority.Authority;
import com.willpowered.eindprojectwpsbe.Authority.AuthorityRepository;
import com.willpowered.eindprojectwpsbe.Exception.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private AuthenticationService authenticationService;


    //////////////////////////////
    //// Create

    public String createUser(UserInputDto dto) {
        try {
            String encryptedPassword = passwordEncoder.encode(dto.password);

            User user = dto.toUser();
            user.setPassword(encryptedPassword);
            user.setEnabled(true);
            user.addAuthority("ROLE_USER");
            for (String s : dto.authorities) {
                if (!s.startsWith("ROLE_")) {
                    s = "ROLE_" + s;
                }
                s = s.toUpperCase();
                if (!s.equals("ROLE_USER")) {
                    user.addAuthority(s);
                }
            }

            User newUser = userRepository.save(user);
            return newUser.getUsername();
        } catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }

    }

    //////////////////////////////
    //// Read

    public List<User> getUsersByRole(String authority, Pageable pageable) {
        List<Authority> authoritiesList = authorityRepository.findAllByAuthority(authority, pageable);
        ArrayList<User> users = new ArrayList<User>();
        for (Authority auth : authoritiesList) {
            var user = userRepository.findByUsername(auth.getUsername());
            user.ifPresent(users::add);
        }
        return users;
    }

    public User getUser(String username) {
        var user = userRepository.findById(username);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new RecordNotFoundException("User does not exist");
        }
    }

    public List<User> getUsers() {
        return (List<User>) userRepository.findAll();
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public User getCurrentUser() {
        String currentUsername = authenticationService.getCurrentUserName();
        return userRepository.findById(currentUsername)
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + currentUsername));
    }

    //////////////////////////////
    //// Update

    public void updateUser(String username, User newUser) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    public void setPassword(PasswordInputDto passwordInputDto) {
        String username = passwordInputDto.userName;
        String password = passwordInputDto.newPassword;

        if (username.equals(authenticationService.getCurrentUserName())) {
            if (isValidPassword(password)) {
                var userOptional = userRepository.findById(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                } else {
                    throw new UserNotFoundException(username);
                }
            } else {
                throw new InvalidPasswordException();
            }
        } else {
            throw new NotAuthorizedException();
        }
    }

    //////////////////////////////
    //// Delete

    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        } else {
            throw new UserNotFoundException(username);
        }
    }

    //// Helpers
    //////////////////////////////

    private boolean isValidPassword(String password) {
        final int MIN_LENGTH = 8;
        final int MIN_DIGITS = 1;
        final int MIN_LOWER = 1;
        final int MIN_UPPER = 1;
        final int MIN_SPECIAL = 1;
        final String SPECIAL_CHARS = "@#$%&*!()+=-_";

        long countDigit = password.chars().filter(ch -> ch >= '0' && ch <= '9').count();
        long countLower = password.chars().filter(ch -> ch >= 'a' && ch <= 'z').count();
        long countUpper = password.chars().filter(ch -> ch >= 'A' && ch <= 'Z').count();
        long countSpecial = password.chars().filter(ch -> SPECIAL_CHARS.indexOf(ch) >= 0).count();

        boolean validPassword = true;
        if (password.length() < MIN_LENGTH) validPassword = false;
        if (countLower < MIN_LOWER) validPassword = false;
        if (countUpper < MIN_UPPER) validPassword = false;
        if (countDigit < MIN_DIGITS) validPassword = false;
        if (countSpecial < MIN_SPECIAL) validPassword = false;

        return validPassword;
    }


}
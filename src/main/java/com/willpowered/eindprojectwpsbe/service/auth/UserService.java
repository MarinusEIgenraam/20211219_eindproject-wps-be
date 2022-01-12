package com.willpowered.eindprojectwpsbe.service.auth;

import com.willpowered.eindprojectwpsbe.dto.auth.UserPostRequestDto;
import com.willpowered.eindprojectwpsbe.exception.BadRequestException;
import com.willpowered.eindprojectwpsbe.exception.InvalidPasswordException;
import com.willpowered.eindprojectwpsbe.exception.NotAuthorizedException;
import com.willpowered.eindprojectwpsbe.exception.UserNotFoundException;
import com.willpowered.eindprojectwpsbe.model.auth.Authority;
import com.willpowered.eindprojectwpsbe.model.auth.User;
import com.willpowered.eindprojectwpsbe.repository.auth.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private String getCurrentUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ((UserDetails) authentication.getPrincipal()).getUsername();
    }

    public Iterable<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(String username) {
        return userRepository.findById(username);
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

    public String createUser(UserPostRequestDto userPostRequest) {
        try {
            String encryptedPassword = passwordEncoder.encode(userPostRequest.getPassword());

            User user = new User();
            user.setUsername(userPostRequest.getUsername());
            user.setPassword(encryptedPassword);
            user.setEmail(userPostRequest.getEmail());
            user.setEnabled(true);
            user.addAuthority("ROLE_USER");
            for (String s : userPostRequest.getAuthorities()) {
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
        }
        catch (Exception ex) {
            throw new BadRequestException("Cannot create user.");
        }

    }

    public void deleteUser(String username) {
        if (userRepository.existsById(username)) {
            userRepository.deleteById(username);
        }
        else {
            throw new UserNotFoundException(username);
        }
    }

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

    public void setPassword(String username, String password) {
        if (username.equals(getCurrentUserName())) {
            if (isValidPassword(password)) {
                Optional<User> userOptional = userRepository.findById(username);
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);
                }
                else {
                    throw new UserNotFoundException(username);
                }
            }
            else {
                throw new InvalidPasswordException();
            }
        }
        else {
            throw new NotAuthorizedException();
        }
    }

    public void updateUser(String username, User newUser) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException();
        User user = userRepository.findById(username).get();
        user.setPassword(newUser.getPassword());
        userRepository.save(user);
    }

    public Set<Authority> getAuthorities(String username) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        return user.getAuthorities();
    }

    public void addAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        user.addAuthority(new Authority(username, authority));
        userRepository.save(user);
    }

    public void removeAuthority(String username, String authority) {
        if (!userRepository.existsById(username)) throw new UserNotFoundException(username);
        User user = userRepository.findById(username).get();
        Authority authorityToRemove = user.getAuthorities().stream().filter((a) -> a.getAuthority().equalsIgnoreCase(authority)).findAny().get();
        user.removeAuthority(authorityToRemove);
        userRepository.save(user);
    }

}
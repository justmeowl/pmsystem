package com.medvedkova.pmsystem.user;

import com.medvedkova.pmsystem.exception.user.UserAlreadyExistException;
import com.medvedkova.pmsystem.exception.user.UserNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserDetailsService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return getUser(username);
    }

    public boolean hasUser(String username) {
        return userRepository.existsByUsername(username);
    }

    public User getUser(String username) {
        return userRepository.findUserByUsername(username)
                .orElseThrow(UserNotFoundException::new);
    }

    public User getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUser(username);
    }

    public void registerNewUser(User user, String confirmPassword) {
        //Check to exist user
        if (hasUser(user.getUsername()))
            throw new UserAlreadyExistException();
        //Validate username and password
        UserValidator.validateUsername(user.getUsername());
        UserValidator.validatePassword(user.getPassword().toCharArray(), confirmPassword.toCharArray());
        //Encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}

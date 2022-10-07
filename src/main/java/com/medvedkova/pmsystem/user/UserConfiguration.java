package com.medvedkova.pmsystem.user;

import com.medvedkova.pmsystem.user.User;
import com.medvedkova.pmsystem.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserConfiguration {

    private final PasswordEncoder passwordEncoder;

    public UserConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public User user1(UserRepository userRepository) {
        User newUser = new User("Sunline", passwordEncoder.encode("123"));
        userRepository.save(newUser);
        return newUser;
    }

    @Bean
    public User user2(UserRepository userRepository) {
        User newUser = new User("Alala", passwordEncoder.encode("123"));
        userRepository.save(newUser);
        return newUser;
    }
}

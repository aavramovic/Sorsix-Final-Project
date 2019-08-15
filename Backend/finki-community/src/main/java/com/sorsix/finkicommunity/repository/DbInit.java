package com.sorsix.finkicommunity.repository;

import com.sorsix.finkicommunity.domain.entities.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        this.userRepository.deleteAll();

        User fisnik = new User("fisnik", passwordEncoder.encode("fisnik123"), "USER", "");
        User antonio = new User("antonio", passwordEncoder.encode("antonio123"), "USER", "");
        User admin = new User("admin", passwordEncoder.encode("admin123"), "ADMIN", "");
        User moderator = new User("moderator", passwordEncoder.encode("moderator123"), "MODERATOR", "");

        List<User> users = Arrays.asList(fisnik, antonio, admin, moderator);

        this.userRepository.saveAll(users);
    }
}

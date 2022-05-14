package main;

import main.entity.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class TestDataInit implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("user", passwordEncoder.encode("password"), Collections.singletonList("ROLE_USER")));
        userRepository.save(new User("admin", passwordEncoder.encode("adminPassword"), Collections.singletonList("ROLE_ADMIN")));
    }
}

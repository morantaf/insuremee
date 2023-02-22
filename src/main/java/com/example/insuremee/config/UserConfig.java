package com.example.insuremee.config;

import com.example.insuremee.domains.Insurance;
import com.example.insuremee.domains.Role;
import com.example.insuremee.domains.User;
import com.example.insuremee.enums.UserRole;
import com.example.insuremee.repository.InsuranceRepository;
import com.example.insuremee.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class UserConfig {

    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository, InsuranceRepository insuranceRepository) {
        return args -> {
            Insurance carglass = new Insurance("Carglass-2000",2000, 20);
            Insurance franprix = new Insurance("Franprix-1000",1000, 10);
            insuranceRepository.saveAll(List.of(carglass,franprix));
            Role admin = new Role(UserRole.ROLE_ADMIN.name());
            Role user = new Role(UserRole.ROLE_USER.name());
            String allPassword = passwordEncoder.encode("password");
            User bob = new User("user1@email.com", allPassword,"Bob", "Morane", Collections.singleton(admin));
            User john = new User("user2@email.com", allPassword,"John", "Doe", franprix, Collections.singleton(user));
            userRepository.saveAll(List.of(bob, john));
        };
    }
}

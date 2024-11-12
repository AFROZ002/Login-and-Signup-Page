package com.useraccess.config;
import com.useraccess.entity.User;
import com.useraccess.entity.UserRole;
import com.useraccess.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializationConfig {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Create admin user if it doesn't exist
            if (!userRepository.existsByUsername("admin")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(UserRole.ADMIN);
                userRepository.save(admin);
            }

            // Create a default manager
            if (!userRepository.existsByUsername("manager")) {
                User manager = new User();
                manager.setUsername("manager");
                manager.setPassword(passwordEncoder.encode("manager123"));
                manager.setRole(UserRole.MANAGER);
                userRepository.save(manager);
            }

            // Create a default employee
            if (!userRepository.existsByUsername("employee")) {
                User employee = new User();
                employee.setUsername("employee");
                employee.setPassword(passwordEncoder.encode("employee123"));
                employee.setRole(UserRole.EMPLOYEE);
                userRepository.save(employee);
            }
        };
    }
}
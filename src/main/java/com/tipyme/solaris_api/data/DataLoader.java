package com.tipyme.solaris_api.data;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.tipyme.solaris_api.roles.Role;
import com.tipyme.solaris_api.roles.repository.RoleRepository;
import com.tipyme.solaris_api.users.User;
import com.tipyme.solaris_api.users.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(DataLoader.class);

    private final UserRepository userRepository;
    private final RoleRepository roleRepositoy;
    private final PasswordEncoder passwordEncoder;

    @Value("${dataloader.password}")
    private String PASSWORD;
    static final String ADMIN_EMAIL = "admin@test.com";
    static final String USER_EMAIL = "user@test.com";

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        Role adminRole = roleRepositoy.findByName("ADMIN")
        .orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("ADMIN");

            return roleRepositoy.save(newRole);
        });

        Role userRole = roleRepositoy.findByName("USER")
        .orElseGet(() -> {
            Role newRole = new Role();
            newRole.setName("USER");

            return roleRepositoy.save(newRole);
        });

        if (userRepository.findByEmail(ADMIN_EMAIL).isEmpty()) {
            User admin = new User();
            admin.setEmail(ADMIN_EMAIL);
            admin.setName("admin");
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode(PASSWORD));

            Set<Role> adminRoles = new HashSet<>();
            adminRoles.add(adminRole);
            adminRoles.add(userRole);

            admin.setRoles(adminRoles);

            userRepository.save(admin);
            logger.info("Admin user created with email: " + ADMIN_EMAIL);
        }

         if (userRepository.findByEmail(USER_EMAIL).isEmpty()) {
            User user = new User();
            user.setEmail(USER_EMAIL);
            user.setName("user");
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode(PASSWORD));

            Set<Role> userRoles = new HashSet<>();
            userRoles.add(adminRole);
            userRoles.add(userRole);

            user.setRoles(userRoles);

            userRepository.save(user);
            logger.info("User created with email: " + USER_EMAIL);
        }

    }
    
}

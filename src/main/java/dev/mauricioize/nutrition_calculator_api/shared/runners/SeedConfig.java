package dev.mauricioize.nutrition_calculator_api.shared.runners;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import dev.mauricioize.nutrition_calculator_api.modules.auth.entity.Role;
import dev.mauricioize.nutrition_calculator_api.modules.auth.enums.ERole;
import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import dev.mauricioize.nutrition_calculator_api.modules.user.repository.UserRepository;
import dev.mauricioize.nutrition_calculator_api.shared.security.RoleRepository;

@Configuration
class SeedConfig {
    private static final Logger logger = LoggerFactory.getLogger(SeedConfig.class);

    @Bean
    CommandLineRunner seedAdmin(UserRepository userRepo, RoleRepository rolesRepo, PasswordEncoder enc) {
        return args -> {
            logger.info("_LOG_ Seeding ADMIN user...");
            String email = "ADMIN";
            if (!userRepo.existsByEmail(email)) {
                Role adminRole = rolesRepo.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow();

                // TODO: Remove hardcoded ADMIN password
                User u = User.builder()
                        .email(email)
                        .passwordHash(enc.encode("ADMIN"))
                        .name("ADMIN")
                        .roles(Set.of(adminRole))
                        .build();

                userRepo.save(u);
            }
        };
    }
}

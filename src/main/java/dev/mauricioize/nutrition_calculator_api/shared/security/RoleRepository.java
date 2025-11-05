package dev.mauricioize.nutrition_calculator_api.shared.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.mauricioize.nutrition_calculator_api.modules.auth.entity.Role;
import dev.mauricioize.nutrition_calculator_api.modules.auth.enums.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}

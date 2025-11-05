package dev.mauricioize.nutrition_calculator_api.modules.auth.service;

import org.springframework.stereotype.Service;

import dev.mauricioize.nutrition_calculator_api.modules.auth.dto.AuthResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.auth.dto.SigninRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.user.dto.UserResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.user.dto.UserSignupRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import dev.mauricioize.nutrition_calculator_api.modules.user.repository.UserRepository;
import dev.mauricioize.nutrition_calculator_api.shared.security.JwtService;
import dev.mauricioize.nutrition_calculator_api.shared.security.PasswordService;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordService passwordService,
            JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordService = passwordService;
        this.jwtService = jwtService;
    }

    public UserResponseDto signup(UserSignupRequestDto dto) {
        String hashed = passwordService.hash(dto.password());

        User user = User.builder()
                .name(dto.name())
                .email(dto.email())
                .passwordHash(hashed)
                .build();

        User saved = userRepository.save(user);

        return new UserResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getEmail());
    }

    public AuthResponseDto signin(SigninRequestDto dto) {
        User user = userRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Invalid credentials"));

        boolean ok = passwordService.matches(dto.password(), user.getPasswordHash());
        if (!ok) {
            throw new RuntimeException("Invalid credentials");
        }

        String token = jwtService.generateToken(user.getEmail());

        return new AuthResponseDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                token);
    }
}
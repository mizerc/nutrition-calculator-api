package dev.mauricioize.nutrition_calculator_api.modules.auth.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mauricioize.nutrition_calculator_api.modules.auth.dto.AuthResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.auth.dto.SigninRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.auth.service.AuthService;
import dev.mauricioize.nutrition_calculator_api.modules.user.dto.UserResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.user.dto.UserSignupRequestDto;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public UserResponseDto create(@Valid @RequestBody UserSignupRequestDto dto) {
        return authService.signup(dto);
    }

    @PostMapping("/signin")
    public AuthResponseDto signin(@Valid @RequestBody SigninRequestDto dto) {
        return authService.signin(dto);
    }
}

package dev.mauricioize.nutrition_calculator_api.modules.user.controller;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import dev.mauricioize.nutrition_calculator_api.modules.user.dto.ProfileResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import dev.mauricioize.nutrition_calculator_api.modules.user.repository.UserRepository;
import dev.mauricioize.nutrition_calculator_api.modules.user.service.AvatarService;

@RequestMapping("/users")
@RestController
public class UserController {
    private final AvatarService avatarService;
    private final UserRepository userRepository;

    public UserController(AvatarService avatarService, UserRepository userRepository) {
        this.avatarService = avatarService;
        this.userRepository = userRepository;
    }

    // TODO: Move to a dedicated profile module
    @GetMapping("/me")
    public ProfileResponseDto me(@AuthenticationPrincipal String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return new ProfileResponseDto(
                user.getId(),
                user.getName(),
                user.getAvatarUrl());
    }

    // TODO: Move to a dedicated profile module
    @PostMapping(value = "/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upload(
            @AuthenticationPrincipal(expression = "") String email,
            @RequestParam("file") MultipartFile file) throws IOException {

        avatarService.updateUserAvatar(email, file);
        return ResponseEntity.ok().build();
    }

    // streaming controller for profile avatars (disabled in flavor of direct file
    // serving by web server)
    // @GetMapping("/avatars/{file}")
    // public ResponseEntity<Resource> serve(@PathVariable String file) throws
    // IOException {
    // Path path = Paths.get("data/avatars", file);
    // UrlResource resource = new UrlResource(path.toUri());
    // return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(resource);
    // }
}

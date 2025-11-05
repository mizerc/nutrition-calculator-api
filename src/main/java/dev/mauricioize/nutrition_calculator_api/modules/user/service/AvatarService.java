package dev.mauricioize.nutrition_calculator_api.modules.user.service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import dev.mauricioize.nutrition_calculator_api.modules.user.repository.UserRepository;
import jakarta.transaction.Transactional;

@Service
public class AvatarService {
    // TODO: Get from config
    private final UserRepository userRepository;
    private final Path storagePath;

    public AvatarService(UserRepository userRepository) throws IOException {
        this.userRepository = userRepository;
        this.storagePath = Paths.get("uploads/avatars");
        Files.createDirectories(storagePath);
    }

    @Transactional
    public void updateUserAvatar(String email, MultipartFile file) throws IOException {
        // User user = userRepository.findById(userId).orElseThrow();
        User user = userRepository.findByEmail(email).orElseThrow();

        String filename = safeFilename(user.getId(), file);
        Path dest = storagePath.resolve(filename);
        Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

        user.setAvatarUrl("/avatars/" + filename);
        userRepository.save(user);
    }

    public String saveAvatar(Long userId, MultipartFile file) {
        validateFile(file);
        String filename = userId + "_" + UUID.randomUUID() + getExt(file);
        Path target = storagePath.resolve(filename);

        try (InputStream in = file.getInputStream()) {
            Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException("Upload error", e);
        }

        return "/avatars/" + filename;
    }

    private String safeFilename(Long userId, MultipartFile file) {
        // Original filename
        String raw = file.getOriginalFilename();
        if (raw == null) {
            raw = "file";
        }

        // Keep only a-z, A-Z, 0-9, dot, underscore
        String cleaned = raw.replaceAll("[^a-zA-Z0-9.]", "");
        if (cleaned.isEmpty()) {
            cleaned = "file";
        }

        // Avoid path traversal
        cleaned = cleaned.replace("..", "");

        return userId + "_" + System.currentTimeMillis() + "_" + cleaned;
    }

    private void validateFile(MultipartFile f) {
        if (f.isEmpty())
            throw new IllegalArgumentException("empty");
        if (!Objects.requireNonNull(f.getContentType()).startsWith("image"))
            throw new IllegalArgumentException("invalid content");
    }

    private String getExt(MultipartFile f) {
        String n = f.getOriginalFilename();
        int i = n.lastIndexOf('.');
        return i == -1 ? "" : n.substring(i);
    }
}

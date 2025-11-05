package dev.mauricioize.nutrition_calculator_api.modules.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
// import java.util.UUID;

import dev.mauricioize.nutrition_calculator_api.modules.auth.entity.Role;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // required for JPA
// @NoArgsConstructor(access = AccessLevel.PUBLIC) // JPA still works because it
// ignores Java visibility via reflection.
@Builder
@AllArgsConstructor // optional convenience
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    // private UUID id;
    private Long id;

    // SMTP rules => 254 characters max
    @Column(unique = true, nullable = false, length = 254)
    private String email;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 512)
    private String avatarUrl;

    // 255 to supports BCrypt/Argon2 encodings
    @Column(nullable = false, length = 255)
    private String passwordHash;

    @Column(updatable = false, nullable = false)
    // private OffsetDateTime createdAt;
    // private Date createdAt;
    private LocalDateTime createdAt;

    @Column(nullable = false)
    // private Date updatedAt;
    // private OffsetDateTime updatedAt;
    private LocalDateTime updatedAt;

    // Link user with roles
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    // JPA life cycle hooks
    @PrePersist
    void prePersist() {
        LocalDateTime now = LocalDateTime.now();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // getters/setters (thru Lombok)

    // Convenience constructor
    // public User(String name, String email, String passwordHash) {
    // this.name = name;
    // this.email = email;
    // this.passwordHash = passwordHash;
    // }

    // REQUIRED BY JPA
    // protected User() {
    // }

}
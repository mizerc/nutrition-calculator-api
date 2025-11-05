package dev.mauricioize.nutrition_calculator_api.modules.food.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "foods")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    // private UUID id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false)
    private Integer portionSizeGrams;

    @Column(nullable = false)
    private Integer kcals;

    // @Column(nullable = false)
    // private String description = "";

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Owner of the meal
    // Link food with one user, many foods can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Builder only for fields you set manually
    @Builder
    public Food(String name, Integer portionSizeGrams, Integer kcals, User user) {
        this.name = name;
        this.portionSizeGrams = portionSizeGrams;
        this.kcals = kcals;
        this.user = user;
    }

    // required by JPA
    // protected Food() {
    // }

    // Constructor
    // public Food(String name, Integer portionSizeGrams, Integer kcals) {
    // this.name = name;
    // this.portionSizeGrams = portionSizeGrams;
    // this.kcals = kcals;
    // }

    // JPA lifecycle callbacks
    // @PrePersist
    // void prePersist() {
    // createdAt = updatedAt = LocalDateTime.now();
    // }
    // @PreUpdate
    // void preUpdate() {
    // updatedAt = LocalDateTime.now();
    // }

    // Getters and Setters (via Lombok)

    // public long getId() {
    // return id;
    // }

    // public void setId(long id) {
    // this.id = id;
    // }

    // public String getName() {
    // return name;
    // }

    // public void setName(String name) {
    // this.name = name;
    // }

    // public Integer getPortionSizeGrams() {
    // return portionSizeGrams;
    // }

    // public void setPortionSizeGrams(Integer portionSizeGrams) {
    // this.portionSizeGrams = portionSizeGrams;
    // }

    // public Integer getKcals() {
    // return kcals;
    // }

    // public void setKcals(Integer kcals) {
    // this.kcals = kcals;
    // }

    // public LocalDateTime getCreatedAt() {
    // return createdAt;
    // }

    // public void setCreatedAt(LocalDateTime createdAt) {
    // this.createdAt = createdAt;
    // }

}

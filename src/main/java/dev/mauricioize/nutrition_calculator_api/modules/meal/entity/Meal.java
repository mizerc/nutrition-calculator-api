package dev.mauricioize.nutrition_calculator_api.modules.meal.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meals")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meal {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 100)
    private String name;

    // List of foods of the meal
    // Link this single meal with many foods
    @OneToMany(mappedBy = "meal", orphanRemoval = true)
    private Set<MealFood> mealItems = new HashSet<>();

    @Column(nullable = false)
    private Integer totalKcals;

    // Owner of the meal
    // Link meal with one user, many meals can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    // Builder only for fields you set manually
    @Builder
    public Meal(String name, Integer totalKcals, User user) {
        this.name = name;
        this.totalKcals = totalKcals;
        this.user = user;
    }
}

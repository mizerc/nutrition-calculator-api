package dev.mauricioize.nutrition_calculator_api.modules.meal.entity;

import dev.mauricioize.nutrition_calculator_api.modules.food.entity.Food;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "meal_foods", uniqueConstraints = @UniqueConstraint(columnNames = { "meal_id", "food_id" }))
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MealFood {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "meal_id")
    private Meal meal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "food_id")
    private Food food;

    @Column(nullable = false)
    private Double quantityGrams;

    @Builder
    public MealFood(Meal meal, Food food, Double quantityGrams) {
        this.meal = meal;
        this.food = food;
        this.quantityGrams = quantityGrams;
    }
}
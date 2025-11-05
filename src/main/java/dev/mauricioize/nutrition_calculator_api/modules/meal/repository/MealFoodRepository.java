package dev.mauricioize.nutrition_calculator_api.modules.meal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.mauricioize.nutrition_calculator_api.modules.meal.entity.MealFood;

public interface MealFoodRepository extends JpaRepository<MealFood, Long> {
    List<MealFood> findByMealId(Long mealId);

    // Batch fetch all MealFood rows for the given list of meal IDs
    List<MealFood> findByMealIdIn(List<Long> mealIds);
}

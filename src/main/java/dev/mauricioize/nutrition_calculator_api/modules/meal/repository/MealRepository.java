package dev.mauricioize.nutrition_calculator_api.modules.meal.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import dev.mauricioize.nutrition_calculator_api.modules.meal.entity.Meal;

public interface MealRepository extends JpaRepository<Meal, Long> {
    List<Meal> findAllByUserId(Long userId);

    Page<Meal> queryAllByUserId(Long userId, Pageable pageable);
}

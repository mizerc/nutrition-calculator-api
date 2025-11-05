package dev.mauricioize.nutrition_calculator_api.modules.food.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import dev.mauricioize.nutrition_calculator_api.modules.food.entity.Food;

public interface FoodRepository extends JpaRepository<Food, Long> {
    @Query("select f from Food f where f.user.id = :uid")
    List<Food> findAllByUserId(@Param("uid") Long uid);
}

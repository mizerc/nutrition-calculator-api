package dev.mauricioize.nutrition_calculator_api.modules.food.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.mauricioize.nutrition_calculator_api.modules.food.entity.Food;
import dev.mauricioize.nutrition_calculator_api.modules.food.repository.FoodRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class GetOneUseCase {
    @Autowired
    private FoodRepository foodRepository;

    public Food execute(Long id) {
        return foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food not found: " + id));
    }
}

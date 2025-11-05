package dev.mauricioize.nutrition_calculator_api.modules.food.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.couchbase.CouchbaseProperties.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.mauricioize.nutrition_calculator_api.modules.food.dto.FoodCreateRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.food.dto.FoodResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.food.entity.Food;
import dev.mauricioize.nutrition_calculator_api.modules.food.repository.FoodRepository;
import dev.mauricioize.nutrition_calculator_api.modules.food.useCases.CreateFoodUseCase;
import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import dev.mauricioize.nutrition_calculator_api.modules.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodService {
    @Autowired
    FoodRepository foodRepository;
    // private final FoodRepository foodRepository;

    @Autowired
    CreateFoodUseCase createFoodUseCase;

    @Autowired
    UserRepository userRepository;

    // public FoodService(FoodRepository foodRepository) {
    // this.foodRepository = foodRepository;
    // }

    @Transactional(readOnly = true)
    public List<FoodResponseDto> getAll(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return foodRepository.findAllByUserId(user.getId()).stream()
                .map(f -> new FoodResponseDto(
                        f.getId(),
                        f.getName(),
                        f.getPortionSizeGrams(),
                        f.getKcals()))
                .toList();

        // return foodRepository.findAll().stream()
        // .map(f -> new FoodResponseDto(f.getId(), f.getName(),
        // f.getPortionSizeGrams(), f.getKcals()))
        // .toList();

        // return foodRepository.findAllByUserId(user.getId()).stream()
        // .map(FoodResponseDto::from)
        // .toList();
    }

    @Transactional(readOnly = true)
    public FoodResponseDto getFoodById(Long id) {
        Food f = foodRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Food not found: " + id));
        return new FoodResponseDto(f.getId(), f.getName(), f.getPortionSizeGrams(), f.getKcals());
    }

    @Transactional
    public FoodResponseDto create(FoodCreateRequestDto dto) {
        return createFoodUseCase.execute(dto);
    }
}

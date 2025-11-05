package dev.mauricioize.nutrition_calculator_api.modules.food.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;

import dev.mauricioize.nutrition_calculator_api.modules.food.dto.FoodCreateRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.food.dto.FoodResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.food.entity.Food;
import dev.mauricioize.nutrition_calculator_api.modules.food.repository.FoodRepository;
import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import dev.mauricioize.nutrition_calculator_api.modules.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateFoodUseCase {
    @Autowired
    FoodRepository foodRepository;
    @Autowired
    UserRepository userRepository;

    public FoodResponseDto execute(FoodCreateRequestDto dto) {
        // UUID id = UUID.randomUUID();
        // return new FoodResponseDto(id, dto.name());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        log.info("HEREEE Authenticated user={}", auth.getName());

        String email = (String) SecurityContextHolder.getContext()
                .getAuthentication()
                .getPrincipal();

        User currentUser = userRepository.findByEmail(email)
                .orElseThrow();

        // Create the food item associated with the current user
        Food food = Food.builder()
                .name(dto.name())
                .portionSizeGrams(dto.portionSizeGrams())
                .kcals(dto.kcals())
                .user(currentUser)
                .build();

        Food saved = foodRepository.save(food);

        return new FoodResponseDto(
                saved.getId(),
                saved.getName(),
                saved.getPortionSizeGrams(),
                saved.getKcals());
    }
}

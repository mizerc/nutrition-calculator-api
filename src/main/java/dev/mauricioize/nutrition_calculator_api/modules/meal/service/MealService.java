package dev.mauricioize.nutrition_calculator_api.modules.meal.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.mauricioize.nutrition_calculator_api.modules.food.entity.Food;
import dev.mauricioize.nutrition_calculator_api.modules.food.repository.FoodRepository;
import dev.mauricioize.nutrition_calculator_api.modules.meal.dto.MealCreateRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.meal.dto.MealItemResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.meal.dto.MealResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.meal.entity.Meal;
import dev.mauricioize.nutrition_calculator_api.modules.meal.entity.MealFood;
import dev.mauricioize.nutrition_calculator_api.modules.meal.repository.MealFoodRepository;
import dev.mauricioize.nutrition_calculator_api.modules.meal.repository.MealRepository;
import dev.mauricioize.nutrition_calculator_api.modules.user.entity.User;
import dev.mauricioize.nutrition_calculator_api.modules.user.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MealService {
        @Autowired
        FoodRepository foodRepository;
        @Autowired
        UserRepository userRepository;
        @Autowired
        MealRepository mealRepository;
        @Autowired
        MealFoodRepository mealFoodRepository;

        @Transactional(readOnly = true)
        public Page<MealResponseDto> getAllPageable(String email, Pageable pageable) {
                User user = userRepository.findByEmail(email).orElseThrow();

                // Get all meals of user
                Page<Meal> page = mealRepository.queryAllByUserId(user.getId(), pageable);
                List<Meal> meals = page.getContent();
                if (meals.isEmpty())
                        return Page.empty(pageable);

                // Collect meal IDs
                List<Long> mealIds = meals.stream()
                                .map(Meal::getId)
                                .toList();

                // Load all MealFood in one query
                List<MealFood> allMealFoodRows = mealFoodRepository.findByMealIdIn(mealIds);

                // Group by mealId
                Map<Long, List<MealFood>> byMeal = allMealFoodRows.stream().collect(
                                Collectors.groupingBy(mf -> mf.getMeal().getId()));

                // Build response
                List<MealResponseDto> dtoList = meals.stream()
                                .map(meal -> {
                                        List<MealFood> mealFoodRows = byMeal.getOrDefault(meal.getId(), List.of());
                                        List<MealItemResponseDto> mealItems = mealFoodRows.stream()
                                                        .map(mf -> new MealItemResponseDto(
                                                                        mf.getFood().getId(),
                                                                        mf.getQuantityGrams()))
                                                        .toList();
                                        return new MealResponseDto(
                                                        meal.getId(),
                                                        meal.getName(),
                                                        mealItems,
                                                        meal.getTotalKcals());
                                })
                                .toList();

                return new PageImpl<>(dtoList, pageable, page.getTotalElements());
        }

        @Transactional(readOnly = true)
        public List<MealResponseDto> getAll(String email) {
                User user = userRepository.findByEmail(email).orElseThrow();

                // Get all meals of user
                List<Meal> meals = mealRepository.findAllByUserId(user.getId());
                if (meals.isEmpty())
                        return List.of();

                // Collect meal IDs
                List<Long> mealIds = meals.stream()
                                .map(Meal::getId)
                                .toList();

                // Load all MealFood in one query
                List<MealFood> allMealFoodRows = mealFoodRepository.findByMealIdIn(mealIds);

                // Group by mealId
                Map<Long, List<MealFood>> byMeal = allMealFoodRows.stream().collect(
                                Collectors.groupingBy(mf -> mf.getMeal().getId()));

                // Build response
                return meals.stream()
                                .map(meal -> {
                                        List<MealFood> mealFoodRows = byMeal.getOrDefault(meal.getId(), List.of());
                                        List<MealItemResponseDto> mealItems = mealFoodRows.stream()
                                                        .map(mf -> new MealItemResponseDto(
                                                                        mf.getFood().getId(),
                                                                        mf.getQuantityGrams()))
                                                        .toList();
                                        return new MealResponseDto(
                                                        meal.getId(),
                                                        meal.getName(),
                                                        mealItems,
                                                        meal.getTotalKcals());
                                })
                                .toList();
        }

        // private MealResponseDto toDto(Meal meal) {
        // // N+1 query here
        // List<MealFood> mealFoods = mealFoodRepository.findByMealId(meal.getId());
        // List<MealItemResponseDto> items = mealFoods.stream()
        // .map(mf -> new MealItemResponseDto(
        // mf.getFood().getId(),
        // mf.getQuantityGrams()))
        // .toList();
        // return new MealResponseDto(
        // meal.getId(),
        // meal.getName(),
        // items,
        // meal.getTotalKcals());
        // }
        // @Transactional(readOnly = true)
        // public List<MealResponseDto> getAllSlow() {
        // TODO: Remove SecurityContextHolder usage in service layer
        // String email = (String) SecurityContextHolder.getContext()
        // .getAuthentication()
        // .getPrincipal();
        // User currentUser = userRepository.findByEmail(email)
        // .orElseThrow();
        // List<Meal> meals = mealRepository.findAllByUserId(currentUser.getId());
        // return meals.stream()
        // .map(meal -> {
        // List<MealFood> mealFoods = mealFoodRepository.findByMealId(meal.getId());
        // List<MealItemResponseDto> items = mealFoods.stream()
        // .map(mf -> new MealItemResponseDto(
        // mf.getFood().getId(),
        // mf.getQuantityGrams()))
        // .toList();
        // return new MealResponseDto(
        // meal.getId(),
        // meal.getName(),
        // items,
        // meal.getTotalKcals());
        // })
        // .toList();
        // return mealRepository.findAllByUserId(currentUser.getId())
        // .stream()
        // .map(this::toDto)
        // .toList();
        // }

        @Transactional(readOnly = true)
        public MealResponseDto getMealById(Long mealId) {
                Meal meal = mealRepository.findById(mealId)
                                .orElseThrow(() -> new EntityNotFoundException("Meal not found"));

                List<MealFood> mealFood = mealFoodRepository.findByMealId(mealId);
                List<MealItemResponseDto> mealItems = mealFood.stream()
                                .map((MealFood mf) -> new MealItemResponseDto(
                                                mf.getFood().getId(),
                                                mf.getQuantityGrams()))
                                .toList();

                return new MealResponseDto(
                                meal.getId(),
                                meal.getName(),
                                mealItems,
                                meal.getTotalKcals());
        }

        // Creates a meal with N+1 queries, not optimal but ok for now
        public MealResponseDto createSlow(MealCreateRequestDto dto) {
                // TODO: Remove SecurityContextHolder usage in service layer
                // 1) Get current user
                String email = (String) SecurityContextHolder.getContext()
                                .getAuthentication()
                                .getPrincipal();
                User currentUser = userRepository.findByEmail(email)
                                .orElseThrow();

                // 2) Create empty meal to get the id
                Meal meal = Meal.builder()
                                .name(dto.name())
                                .user(currentUser)
                                .totalKcals(0)
                                .build();
                meal = mealRepository.save(meal);

                // 3) For each food+quantity (N+1 queries, ok for now)
                int totalKcals = 0;
                for (MealItemResponseDto mealItem : dto.mealItems()) {
                        Food food = foodRepository.findById(mealItem.foodId())
                                        .orElseThrow();

                        // 3a) Check food belongs to same user
                        if (!food.getUser().getId().equals(currentUser.getId())) {
                                throw new RuntimeException("Food is not owned by user");
                        }

                        // 3b) Populate joint table
                        MealFood mf = MealFood.builder()
                                        .meal(meal)
                                        .food(food)
                                        .quantityGrams(mealItem.quantityGrams())
                                        .build();
                        mealFoodRepository.save(mf);

                        // 3c) Compute the kcals of the food portion
                        double factor = mealItem.quantityGrams() / food.getPortionSizeGrams();

                        // 3d) Accumulate total kcals
                        totalKcals += Math.round(food.getKcals() * factor);
                }

                // 3) Update meal summary
                meal.setTotalKcals(totalKcals);
                Meal saved = mealRepository.save(meal);

                // 4) Load all foods of meal for response
                return this.getMealById(saved.getId());

        }
}

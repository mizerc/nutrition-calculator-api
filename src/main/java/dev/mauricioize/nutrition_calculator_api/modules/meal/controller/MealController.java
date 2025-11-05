package dev.mauricioize.nutrition_calculator_api.modules.meal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mauricioize.nutrition_calculator_api.modules.meal.dto.MealCreateRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.meal.dto.MealResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.meal.service.MealService;
import jakarta.validation.Valid;

@RequestMapping("/meals")
@RestController
public class MealController {
    @Autowired
    MealService mealService;

    // @GetMapping
    // public List<MealResponseDto> getAll(@AuthenticationPrincipal(expression = "")
    // String email) {
    // return mealService.getAll(email);
    // }

    @GetMapping
    public Page<MealResponseDto> getAll(@AuthenticationPrincipal(expression = "") String email, Pageable pageable) {
        return mealService.getAllPageable(email, pageable);
    }

    // @GetMapping
    // public List<MealResponseDto> getAll(Authentication auth) {
    // return mealService.getAll(auth.getName());
    // }

    @GetMapping("/{id}")
    public MealResponseDto getOne(@PathVariable Long id) {
        return mealService.getMealById(id);
    }

    @PostMapping("/create")
    public MealResponseDto create(@Valid @RequestBody MealCreateRequestDto dto) {
        return mealService.createSlow(dto);
    }
}

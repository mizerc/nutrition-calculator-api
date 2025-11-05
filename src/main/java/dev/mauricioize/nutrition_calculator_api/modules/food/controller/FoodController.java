package dev.mauricioize.nutrition_calculator_api.modules.food.controller;

import java.util.List;
// import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.mauricioize.nutrition_calculator_api.modules.food.dto.FoodCreateRequestDto;
import dev.mauricioize.nutrition_calculator_api.modules.food.dto.FoodResponseDto;
import dev.mauricioize.nutrition_calculator_api.modules.food.service.FoodService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/foods")
@RestController
public class FoodController {

    @Autowired
    FoodService foodService;

    @GetMapping
    public List<FoodResponseDto> getAll(Authentication auth) {
        return foodService.getAll(auth.getName());
    }

    @GetMapping("/{id}")
    public FoodResponseDto getOne(@PathVariable Long id) {
        return foodService.getFoodById(id);
    }

    @PostMapping("/create")
    public FoodResponseDto create(@Valid @RequestBody FoodCreateRequestDto dto) {
        return foodService.create(dto);
    }

}

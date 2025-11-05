package dev.mauricioize.nutrition_calculator_api.shared.runners;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Print implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        System.out.println("_LOG_ Nutrition Calculator API is running...");
    }
    
}

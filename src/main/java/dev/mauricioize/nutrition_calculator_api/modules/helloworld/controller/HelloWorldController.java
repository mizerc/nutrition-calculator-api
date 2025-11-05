package dev.mauricioize.nutrition_calculator_api.modules.helloworld.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;


@RequestMapping("/helloworld")
@RestController
public class HelloWorldController {
    @GetMapping()
    public String helloworld() {
        return "Hello, World!";
    }
}

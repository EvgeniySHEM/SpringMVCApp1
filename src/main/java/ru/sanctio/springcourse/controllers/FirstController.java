package ru.sanctio.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
         model.addAttribute("message", "Hello, " + name + " " + surname);
        return "first/hello";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam("first") int first,
                             @RequestParam("second") int second,
                             @RequestParam("action") String action,
                             Model model) {
        model.addAttribute("action", action);
        model.addAttribute("result", getResult(first, second, action));
        return "first/calculator";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    private double getResult(int a, int b, String action) {
        return switch (action) {
            case "multiplication" -> a * b;
            case "addition" -> a + b;
            case "subtraction" -> a - b;
            case "division" -> (double) a / b;
            default -> throw new IllegalStateException("Unexpected value: " + action);
        };
    }
}

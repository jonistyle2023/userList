package com.springmvc.app.springmvc.app.controllers;

import com.springmvc.app.springmvc.app.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class UserController { // Singleton

    @GetMapping({"/view", "/", "/another"})
    public String view(Model model) {
        model.addAttribute("title", "Hola mundo en spring Boot"); // Page name
        model.addAttribute("message", "Esta es una aplicación de ejemplo usando Spring MVC!"); // Content
        model.addAttribute("user", new User("Jonathan", "Panchana"));
        return "view";
    }
}

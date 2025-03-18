package com.springmvc.app.springmvc.app.controllers;

import com.springmvc.app.springmvc.app.models.User;
import com.springmvc.app.springmvc.app.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController { // Singleton

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping({"/view", "/another"})
    public String view(Model model) {
        model.addAttribute("title", "Hola mundo en spring Boot"); // Page name
        model.addAttribute("message", "Esta es una aplicación de ejemplo usando Spring MVC!"); // Content
        model.addAttribute("user", new User("Jonathan", "Panchana"));
        return "view";
    }

    @GetMapping
    public String list(Model model) {
        model.addAttribute("title", "Listado de usuarios");
        model.addAttribute("users", service.findAll());
        return "list";
    }

    @GetMapping("/form")
    public String form(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("title", "Formulario de usuario");
        return "form";
    }

    @GetMapping("/form/{id}")
    public String form(@PathVariable Long id, Model model, RedirectAttributes redirect) {
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()) {
            model.addAttribute("user", optionalUser.get());
            model.addAttribute("title", "Editar usuario");
            return "form";
        } else {
            redirect.addFlashAttribute("error", "El usuario con id" +
                    id +
                    " no existe en la base de datos!");
            return "redirect:/Users";
        }
    }

    @PostMapping
    public String form(User user, Model model, RedirectAttributes redirect) {

        String message = (user.getId() > 0)? "El usuario  + user.getUsername() + se ha actualizado correctamente" : "El usuario " + user.getUsername() + " se ha creado correctamente";

        service.save(user);
        redirect.addFlashAttribute("success", message);
        return "redirect:/users";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        Optional<User> optionalUser = service.findById(id);
        if (optionalUser.isPresent()) {
            redirect.addFlashAttribute("success", "El usuario " + optionalUser.get().getUsername() + " ha sido eliminado correctamente");
            service.deleteBy(id);
            return "redirect:/users";
        }
        redirect.addFlashAttribute("error", "El usuario con el id" +
                id +
                " no existe en el sistema");
        return "redirect:/users";
    }
}

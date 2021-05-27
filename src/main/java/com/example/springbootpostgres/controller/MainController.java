package com.example.springbootpostgres.controller;

import com.example.springbootpostgres.model.User;
import com.example.springbootpostgres.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

    @PostMapping("/registered")
    public String registerFaculty(@Valid User user, BindingResult result, Model model,
                                  HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
        if(result.hasErrors()) {
            return"redirect:/";
        }
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "Registered");
        return "redirect:/";
    }
}

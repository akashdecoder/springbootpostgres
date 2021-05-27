package com.example.springbootpostgres.controller;

import com.example.springbootpostgres.model.Roles;
import com.example.springbootpostgres.model.User;
import com.example.springbootpostgres.repository.RolesRepository;
import com.example.springbootpostgres.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;


    @GetMapping("/")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register.html";
    }

//    @PostMapping("/registered")
//    public String registerFaculty(@Valid User user, BindingResult result, Model model,
//                                  RedirectAttributes redirectAttributes) {
//        if(result.hasErrors()) {
//            return"redirect:/";
//        }
//        userRepository.save(user);
//        redirectAttributes.addFlashAttribute("message", "Registered");
//        return "redirect:/";
//    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users.html";
    }

    @PostMapping("/registered")
    public String registerFaculty(@Valid User user, BindingResult result, Model model,
                                  HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {

        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("warning", "Error Occured");
            return"redirect:/";
        }

        user.setEnabled(1);
        user.setRole("ADMIN");
        Roles roles = rolesRepository.findByRole_name("ADMIN");
        user.addRole(roles);
        user.setEmail(user.getUsername());

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "User Registered");
        return "redirect:/";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login.html";
        }
        return "redirect:/";
    }

    @GetMapping("/logged_out")
    public String loggedOut() {
        return "/";
    }
}

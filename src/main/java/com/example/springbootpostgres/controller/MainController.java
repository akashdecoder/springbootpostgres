package com.example.springbootpostgres.controller;

import com.example.springbootpostgres.model.User;
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

    @GetMapping("/login")
    public String showLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "login.html";
        }
        return "redirect:/";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "users.html";
    }

    @PostMapping("/registered")
    public String registerFaculty(@Valid User user, BindingResult result, Model model,
                                  HttpServletRequest request, RedirectAttributes redirectAttributes) throws IOException {
//        String c_pass = request.getParameter("c_password");
//        String err = validationService.validatePassword(user, c_pass);
//        if(!err.isEmpty()) {
//            ObjectError error = new ObjectError("validationError", err);
//            result.addError(error);
//        }
//        if(result.hasErrors()) {
//            return "/register";
//        }

//        String email = request.getParameter("email");
//        if(isPresent(email) == false) {
//            ObjectError error = new ObjectError("invalidemail", "Email not valid");
//            result.addError(error);
//            redirectAttributes.addFlashAttribute("warning", "Email Invalid");
//        }

        if(result.hasErrors()) {
            redirectAttributes.addFlashAttribute("warning", "Erro Occured");
            return"redirect:/";
        }

        user.setEnabled(1);
        user.setRole("ADMIN");

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        redirectAttributes.addFlashAttribute("message", "User Registered");
        return "redirect:/";
    }
}

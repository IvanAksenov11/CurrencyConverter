package com.example.currencyconverter.controller;

import com.example.currencyconverter.domain.User;
import com.example.currencyconverter.repos.UserRepo;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.Console;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping()
    public String Registration(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping()
    public String addUser(User user, Model model) {
        User userFromDB = userRepo.findByUsername(user.getUsername());

        System.out.println("found");
        if(userFromDB != null) {
            System.out.println("have problem's");
            model.addAttribute("message", "User exists");
            return "registration";
        }

        userRepo.save(user);
        System.out.println("saved");
        return "redirect:/converter";
    }


}

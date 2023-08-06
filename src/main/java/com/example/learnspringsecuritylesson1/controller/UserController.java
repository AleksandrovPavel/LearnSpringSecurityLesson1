package com.example.learnspringsecuritylesson1.controller;

import com.example.learnspringsecuritylesson1.model.Person;
import com.example.learnspringsecuritylesson1.service.PersonService;
import org.springframework.ui.Model;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final PersonService personService;

    public UserController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String getCurrentUser(@AuthenticationPrincipal Person user, Model model) {
        model.addAttribute("person", personService.getPersonById(user.getId()));
        model.addAttribute("car", personService.getInPersonCars(user.getId()));
      return "user/user";
    }
}

package com.example.learnspringsecuritylesson1.controller;

import com.example.learnspringsecuritylesson1.dto.PersonDto;
import com.example.learnspringsecuritylesson1.model.Person;
import com.example.learnspringsecuritylesson1.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonService personService;

    public AuthController(PersonService personService) {
        this.personService = personService;
    }


    @GetMapping("/login")
    public String login() {
        return "person/login";
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("person") PersonDto personDto) {
        return "person/register";
    }


    @PostMapping("/register")
    public String redirectView(@ModelAttribute("person") @Valid PersonDto personDto,
                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "person/register";
        }
        personService.savePerson(personDto);
        return "redirect:/auth/login";
    }

}



package com.example.learnspringsecuritylesson1.controller;


import com.example.learnspringsecuritylesson1.dto.CarDto;
import com.example.learnspringsecuritylesson1.dto.PersonDto;
import com.example.learnspringsecuritylesson1.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("persons", personService.getAllPersons());
        return "person/all_persons";
    }

    @GetMapping("/{person-id}")
    public String getUserById(@PathVariable("person-id") Long personId,
                              @ModelAttribute("new_car") CarDto carDto,
                              Model model) {
        model.addAttribute("person", personService.getPersonById(personId));
        model.addAttribute("car", personService.getInPersonCars(personId));
        return "person/person_by_id";
    }

    @PostMapping("/{person-id}/new_car_to_person")
    public String newCarToUser(@PathVariable("person-id") Long personId,
                               @ModelAttribute("new_car") @Valid CarDto carDto,
                               BindingResult bindingResult, Model model) {
        model.addAttribute("person", personService.getPersonById(personId));
        model.addAttribute("car", personService.getInPersonCars(personId));
        if (bindingResult.hasErrors()) {
            return "person/person_by_id";
        }
        personService.saveCarToPerson(personId, carDto);
        return "redirect:/person/" + personId;
    }

    @GetMapping("/new_person")
    public String newUser(@ModelAttribute("person") PersonDto personDto) {
        return "person/new_person";
    }

    @PostMapping("/save_person")
    public String saveUser(@ModelAttribute("person") @Valid PersonDto personDto,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "person/new_person";
        }
        personService.savePerson(personDto);
        return "redirect:/person";
    }

    @GetMapping("/{person-id}/update_person")
    public String edit(@PathVariable("person-id") Long personId, Model model) {
        model.addAttribute("person", personService.getPersonById(personId));
        return "person/update_person";
    }

    @PostMapping("/{person-id}")
    public String update(@ModelAttribute("update_person") @Valid PersonDto personDto,
                         BindingResult bindingResult,
                         @PathVariable("person-id") Long personId) {
        if (bindingResult.hasErrors()) {
            return "person/update_person";
        }
        personService.updatePerson(personId, personDto);
        return "redirect:/person/" + personId;
    }

    @GetMapping("/{person-id}/delete_person")
    public String delete(@PathVariable("person-id") Long carId) {
        personService.deletePerson(carId);
        return "redirect:/person";
    }


}

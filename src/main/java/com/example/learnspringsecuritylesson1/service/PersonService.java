package com.example.learnspringsecuritylesson1.service;


import com.example.learnspringsecuritylesson1.dto.CarDto;
import com.example.learnspringsecuritylesson1.dto.PersonDto;
import com.example.learnspringsecuritylesson1.model.Car;
import com.example.learnspringsecuritylesson1.model.Person;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface PersonService extends UserDetailsService {
    List<Person> getAllPersons();

    void savePerson(PersonDto personDto);

    Person getPersonById(Long personId);

    void updatePerson(Long personId, PersonDto personDto);

    void deletePerson(Long personId);

    List<Car> getInPersonCars(Long personId);

    void saveCarToPerson(Long personId, CarDto carDto);

    Optional<Person> findUserByUsername(String username);
}

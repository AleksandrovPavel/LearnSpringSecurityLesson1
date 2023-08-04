package com.example.learnspringsecuritylesson1.service;


import com.example.learnspringsecuritylesson1.dto.CarDto;
import com.example.learnspringsecuritylesson1.dto.PersonDto;
import com.example.learnspringsecuritylesson1.model.Car;
import com.example.learnspringsecuritylesson1.model.Person;
import com.example.learnspringsecuritylesson1.repositories.CarRepository;
import com.example.learnspringsecuritylesson1.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final CarRepository carRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository,
                             CarRepository carRepository, @Lazy PasswordEncoder passwordEncoder) {
        this.personRepository = personRepository;
        this.carRepository = carRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Person getPersonById(Long personId) {
        return personRepository.findById(personId).orElseThrow();
    }

    @Override
    @Transactional
    public void savePerson(PersonDto personDto) {
        Person person = new Person();
        person.setLastName(personDto.getLastName());
        person.setFirstName(personDto.getFirstName());
        person.setUsername(personDto.getUsername());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        personRepository.save(person);
    }

    @Override
    @Transactional
    public void updatePerson(Long personId, PersonDto personDto) {
        Person personUpdate = personRepository.findById(personId).orElseThrow();
        personUpdate.setFirstName(personDto.getFirstName());
        personUpdate.setLastName(personDto.getLastName());
        personUpdate.setPassword(passwordEncoder.encode(personUpdate.getPassword()));
        personRepository.save(personUpdate);
    }

    @Override
    @Transactional
    public void deletePerson(Long personId) {
        Person personDeleted = personRepository.findById(personId).orElseThrow();
        personRepository.delete(personDeleted);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Car> getInPersonCars(Long personId) {
        Person person = personRepository.findById(personId).orElseThrow();
        return carRepository.findAllByPerson(person);
    }


    @Override
    @Transactional
    public void saveCarToPerson(Long personId, CarDto carDto) {
        Person person = personRepository.findById(personId).orElseThrow();
        Car car = new Car();
        car.setModel(carDto.getModel());
        car.setSeries(carDto.getSeries());
        car.setPerson(person);
        carRepository.save(car);
    }

    @Override
    public Optional<Person> findUserByUsername(String username) {
        return personRepository.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return personRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User doesn't exists"));
    }
}

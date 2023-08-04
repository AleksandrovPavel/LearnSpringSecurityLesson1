package com.example.learnspringsecuritylesson1.repositories;


import com.example.learnspringsecuritylesson1.model.Car;
import com.example.learnspringsecuritylesson1.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByPerson(Person person);

}

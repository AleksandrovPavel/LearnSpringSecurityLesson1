package com.example.learnspringsecuritylesson1.service;


import com.example.learnspringsecuritylesson1.dto.CarDto;
import com.example.learnspringsecuritylesson1.model.Car;

import java.util.List;

public interface CarService {

    List<Car> getAllCars();

    Car getCarById(Long carId);

    void updateCar(Long carId, CarDto carDto);

    void deleteCar(Long carId);

    Long idPerson(Long carId);
}

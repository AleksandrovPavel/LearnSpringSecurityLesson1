package com.example.learnspringsecuritylesson1.service;


import com.example.learnspringsecuritylesson1.dto.CarDto;
import com.example.learnspringsecuritylesson1.model.Car;
import com.example.learnspringsecuritylesson1.model.Person;
import com.example.learnspringsecuritylesson1.repositories.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Car getCarById(Long carId) {
        return carRepository.findById(carId).orElseThrow();
    }

    @Override
    @Transactional
    public void updateCar(Long carId, CarDto carDto) {
        Car carUpdate = carRepository.findById(carId).orElseThrow();
        carUpdate.setModel(carDto.getModel());
        carUpdate.setSeries(carDto.getSeries());
        carRepository.save(carUpdate);
    }

    @Override
    @Transactional
    public void deleteCar(Long carId) {
        Car carDeleted = carRepository.findById(carId).orElseThrow();
        carRepository.delete(carDeleted);
    }

    @Override
    @Transactional(readOnly = true)
    public Long idPerson(Long carId) {
        long number;
        Car car = carRepository.findById(carId).orElseThrow();
        Person person = car.getPerson();
        number = person.getId();
        return number;
    }
}

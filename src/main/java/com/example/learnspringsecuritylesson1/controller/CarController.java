package com.example.learnspringsecuritylesson1.controller;


import com.example.learnspringsecuritylesson1.dto.CarDto;
import com.example.learnspringsecuritylesson1.service.CarServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/car")
public class CarController {

    private final CarServiceImpl carService;

    @Autowired
    public CarController(CarServiceImpl carService) {
        this.carService = carService;
    }

    @GetMapping()
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "car/all_cars";
    }

    @GetMapping("/{car-id}")
    public String getCarById(@PathVariable("car-id") Long carId, Model model) {
        model.addAttribute("car", carService.getCarById(carId));
        return "car/car_by_id";
    }

    @GetMapping("/{car-id}/transition")
    public String transition(@PathVariable("car-id") Long carId) {
        long number = carService.idPerson(carId);
        return "redirect:/person/" + number;
    }


    @GetMapping("/{car-id}/update")
    public String edit(@PathVariable("car-id") Long carId, Model model) {
        model.addAttribute("car", carService.getCarById(carId));
        return "car/update_car";
    }

    @PostMapping("/{car-id}/car_update")
    public String update(@PathVariable("car-id") Long carId,
                         @ModelAttribute("update_car") @Valid CarDto carDto,
                         BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "car/update_car";
        }
        long number = carService.idPerson(carId);
        carService.updateCar(carId, carDto);
        return "redirect:/person/" + number;
    }
    @GetMapping("/{car-id}/delete_car")
    public String delete(@PathVariable("car-id") Long carId) {
        long number = carService.idPerson(carId);
        carService.deleteCar(carId);
        return "redirect:/person/" + number;
    }

}

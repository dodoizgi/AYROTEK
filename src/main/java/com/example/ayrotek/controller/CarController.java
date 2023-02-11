package com.example.ayrotek.controller;

import com.example.ayrotek.service.CarService;
import com.example.ayrotek.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getCars() {
        return carService.getCars();
    }

    @PostMapping
    public void registerNewCar(@RequestBody Car car) {
        carService.addNewCar(car);}

    @DeleteMapping(path = {"{carID}"})
    public void deleteCar(@PathVariable("carID") int carID) {
        carService.deleteCar(carID);
    }

    @PutMapping(path = "{carID}")
    public void updateCar(
            @PathVariable("carID") int carID,
            @RequestParam(required = false) String carName,
            @RequestParam(required = false) String carYear) {
        carService.updateCar(carID, carName, carYear);
    }
}

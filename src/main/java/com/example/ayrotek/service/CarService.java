package com.example.ayrotek.service;

import com.example.ayrotek.model.Car;
import com.example.ayrotek.repository.CarRepository;
import com.example.ayrotek.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class CarService {

    private final CarRepository carRepository;
    private UserRepository userRepository;

    @Autowired
    public void UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    public List<Car> getCars() {
        return carRepository.findAll();
    }

    public void addNewCar(Car car) {
        carRepository.save(car);
    }

    public void deleteCar(int carId) {
        boolean exists = carRepository.existsById(carId);
        if (!exists) {
            throw new IllegalStateException(
                    "car with id " + carId + " does not exits"
            );
        }
        carRepository.deleteById(carId);
    }

    @Transactional
    public void updateCar(int userId, int carId, String carName, String carYear) {
        userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException(
                        "user with id " + userId + " does not exists"
                ));

        Car car = carRepository.findById(carId).orElseThrow(() ->
                new IllegalStateException(
                "car with id " + carId + " does not exists"
        ));

        if (carName != null &&
                !Objects.equals(car.getName(),carName)) {
            car.setName(carName);
        }

        if (carYear != null &&
                !Objects.equals(car.getYear(),carYear)) {
            car.setYear(carYear);
        }
    }
}

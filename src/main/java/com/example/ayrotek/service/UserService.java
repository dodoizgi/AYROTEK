package com.example.ayrotek.service;

import com.example.ayrotek.model.Car;
import com.example.ayrotek.model.UserModule;
import com.example.ayrotek.repository.CarRepository;
import com.example.ayrotek.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    @Autowired
    public UserService(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }
    public List<UserModule> getUsers() {
        return userRepository.findAll();
    }

    public List<UserModule> getUsersByMailAndPassword(String mail, String password) {
        List<UserModule> UserModuleOptinal = userRepository.findUserModuleByMailAndPassword(mail, password);
        if (UserModuleOptinal.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return userRepository.findUserModuleByMailAndPassword(mail, password);
    }

    public List<Car> getUserCars(int id) {
        List<Car> advertList = carRepository.findCarById(id);
        if (advertList.isEmpty()) {
            throw new IllegalStateException("Car not found");
        }
        return carRepository.findCarById(id);
    }

    public void addNewUser(UserModule userModule) {
        userRepository.save(userModule);
    }

    public void deleteUser(int userId) {
        boolean exists = userRepository.existsById(userId);
        if (!exists) {
            throw new IllegalStateException(
                    "car with id " + userId + " does not exits"
            );
        }
        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateuser(int userId, String name, String lastname, String mail, String password, String phone) {
        UserModule user = userRepository.findById(userId).orElseThrow(() ->
                new IllegalStateException(
                        "user with id " + userId + " does not exists"
                ));

        if (name != null &&
                !Objects.equals(user.getName(),name)) {
            user.setName(name);
        }

        if (lastname != null &&
                !Objects.equals(user.getLastname(),lastname)) {
            user.setLastname(lastname);
        }

        if (mail != null &&
                !Objects.equals(user.getMail(),mail)) {
            user.setMail(mail);
        }

        if (password != null &&
                !Objects.equals(user.getPassword(),password)) {
            user.setPassword(password);
        }

        if (phone != null &&
                !Objects.equals(user.getPhone(),phone)) {
            user.setPhone(phone);
        }

    }
}

package ru.job4j.carsalesplatform.service;

import ru.job4j.carsalesplatform.model.SellingCar;

import java.util.List;

public interface ValidateSellingCar {
    int addCar(SellingCar car);
    void updateCar(SellingCar car);
    void deleteCar(SellingCar car);
    List<SellingCar> findAllCars();
    SellingCar findCarById(int id);
    void changeSaleStatus(int id);
}

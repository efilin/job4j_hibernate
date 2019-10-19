package ru.job4j.carsalesplatform.service;

import ru.job4j.carsalesplatform.dao.SellingCarDao;
import ru.job4j.carsalesplatform.dao.SellingCarDaoImpl;
import ru.job4j.carsalesplatform.model.SellingCar;

import java.util.List;

public class ValidateSellingCarImpl implements ValidateSellingCar {

    private final SellingCarDao logic = new SellingCarDaoImpl();
    private final static ValidateSellingCar INSTANCE = new ValidateSellingCarImpl();

    public static ValidateSellingCar getInstance() {
        return INSTANCE;
    }

    @Override
    public int addCar(SellingCar car) {
        return this.logic.addCar(car);
    }

    @Override
    public void updateCar(SellingCar car) {
        this.logic.updateCar(car);
    }

    @Override
    public void deleteCar(SellingCar car) {
        this.logic.deleteCar(car);
    }

    @Override
    public List<SellingCar> findAllCars() {
        return this.logic.findAllCars();
    }

    @Override
    public SellingCar findCarById(int id) {
        return this.logic.findCarById(id);
    }

    @Override
    public void changeSaleStatus(int id) {
        this.logic.changeSaleStatus(id);
    }
}

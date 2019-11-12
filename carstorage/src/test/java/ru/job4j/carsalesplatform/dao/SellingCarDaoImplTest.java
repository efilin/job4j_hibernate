package ru.job4j.carsalesplatform.dao;

import org.junit.Test;
import ru.job4j.carsalesplatform.model.Seller;
import ru.job4j.carsalesplatform.model.SellingCar;


import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SellingCarDaoImplTest {
    private final SellingCarDao sellingCarDao = new SellingCarDaoImpl();
    private final SellerDao sellerDao = new SellerDaoImpl();

    @Test
    public void addCarAndFindAll() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        SellingCar car = new SellingCar(1, "VW", "Passat", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, null);
        this.sellingCarDao.addCar(car);
        List<SellingCar> sellingCars = this.sellingCarDao.findAllCars();
        SellingCar sellingCar = sellingCars.get(sellingCars.size() - 1);
        assertThat(sellingCar.getModel(), is(car.getModel()));
        this.sellingCarDao.deleteCar(car);
        this.sellerDao.deleteSeller(seller);
    }
}
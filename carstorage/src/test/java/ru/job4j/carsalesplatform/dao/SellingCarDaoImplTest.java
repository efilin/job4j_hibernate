package ru.job4j.carsalesplatform.dao;

import org.junit.Test;
import ru.job4j.carsalesplatform.model.Seller;
import ru.job4j.carsalesplatform.model.SellingCar;


import java.sql.Timestamp;
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
    }

    @Test
    public void whenUpdateCar() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        SellingCar car = new SellingCar(1, "VW", "Passat", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, null);
        this.sellingCarDao.addCar(car);
        car.setManufacturer("Audi");
        this.sellingCarDao.updateCar(car);
        List<SellingCar> sellingCars = this.sellingCarDao.findAllCars();
        SellingCar sellingCar = sellingCars.get(sellingCars.size() - 1);
        assertThat(sellingCar.getManufacturer(), is("Audi"));
    }

    @Test
    public void whenDeleteCar() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        SellingCar car = new SellingCar(1, "VW", "Passat", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, null);
        this.sellingCarDao.addCar(car);
        SellingCar carTwo = new SellingCar(2, "Audi", "A4", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, null);
        this.sellingCarDao.addCar(carTwo);
        this.sellingCarDao.deleteCar(carTwo);
        List<SellingCar> sellingCars = this.sellingCarDao.findAllCars();
        SellingCar sellingCar = sellingCars.get(sellingCars.size() - 1);
        assertThat(sellingCar.getManufacturer(), is("VW"));
    }

    @Test
    public void whenFindLastDayCars() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Timestamp before = new Timestamp(System.currentTimeMillis() - 48 * 3600 * 1000);
        SellingCar car = new SellingCar(1, "MB", "E200", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, now);
        this.sellingCarDao.addCar(car);
        SellingCar carTwo = new SellingCar(2, "Opel", "Corsa", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, before);
        this.sellingCarDao.addCar(carTwo);
        List<SellingCar> sellingCars = this.sellingCarDao.findLastDayCars();
        SellingCar sellingCar = sellingCars.get(sellingCars.size() - 1);
        assertThat(sellingCar.getManufacturer(), is("MB"));

    }

    @Test
    public void whenFindCarsWithPhoto() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        SellingCar car = new SellingCar(1, "VW", "Passat", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, null);
        this.sellingCarDao.addCar(car);
        SellingCar carTwo = new SellingCar(2, "Audi", "A4", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "", seller, true, null);
        this.sellingCarDao.addCar(carTwo);
        List<SellingCar> sellingCars = this.sellingCarDao.findCarsWithPhoto();
        SellingCar sellingCar = sellingCars.get(sellingCars.size() - 1);
        assertThat(sellingCar.getModel(), is("Passat"));

    }

    @Test
    public void findCurrentManufacturerCars() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        SellingCar car = new SellingCar(1, "VW", "Passat", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, null);
        this.sellingCarDao.addCar(car);
        SellingCar carTwo = new SellingCar(2, "Audi", "A4", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "", seller, true, null);
        this.sellingCarDao.addCar(carTwo);
        List<SellingCar> sellingCars = this.sellingCarDao.findCurrentManufacturerCars("Audi");
        SellingCar sellingCar = sellingCars.get(sellingCars.size() - 1);
        assertThat(sellingCar.getModel(), is("A4"));
    }

    @Test
    public void whenFindCarById() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        SellingCar car = new SellingCar(1, "VW", "Passat", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, true, null);
        this.sellingCarDao.addCar(car);
        SellingCar carTwo = new SellingCar(2, "Audi", "A4", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "", seller, true, null);
        this.sellingCarDao.addCar(carTwo);
        SellingCar sellingCar = this.sellingCarDao.findAllCars().get(0);
        int id = sellingCar.getId();
        assertThat(this.sellingCarDao.findCarById(id).getModel(), is(sellingCar.getModel()));
    }

    @Test
    public void whenChangeSaleStatus() {
        Seller seller = new Seller(1, "testName", "testLogin", "testPassword", 555, null);
        this.sellerDao.addSeller(seller);
        SellingCar car = new SellingCar(1, "VW", "Passat", 150000, 20000, 1997, "Sedan", "1,6", "Manual", "Bad condition", "test.jpg", seller, false, null);
        this.sellingCarDao.addCar(car);
        int id = this.sellingCarDao.findAllCars().get(0).getId();
        this.sellingCarDao.changeSaleStatus(id);
        assertTrue(this.sellingCarDao.findAllCars().get(0).isOnSale());
    }
}
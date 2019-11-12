package ru.job4j.carsalesplatform.controller;

import ru.job4j.carsalesplatform.model.SellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCarImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarListServlet extends HttpServlet {

    private final ValidateSellingCar validateSellingCar = ValidateSellingCarImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/CarsView.jsp").forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String filter = req.getParameter("filter");
        String manufacturer = "";
        if (filter.equals("currentManufacturer")) {
            manufacturer = req.getParameter("manufacturers");
        }
        List<SellingCar> carList = filter(filter, manufacturer);
        req.setAttribute("allCars", carList);
        doGet(req, resp);
    }

    private List<SellingCar> filter(String filter, String manufacturer) {
        List<SellingCar> result = new ArrayList<>();
        if (filter.equals("allCars")) {
            result = validateSellingCar.findAllCars();
        } else if (filter.equals("lastDay")) {
            result = validateSellingCar.findLastDayCars();
        } else if (filter.equals("withPhoto")) {
            result = validateSellingCar.findCarsWithPhoto();
        } else if (filter.equals("currentManufacturer")) {
            result = validateSellingCar.findCurrentManufacturerCars(manufacturer);
        }
        return result;
    }
}

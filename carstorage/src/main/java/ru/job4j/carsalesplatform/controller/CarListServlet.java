package ru.job4j.carsalesplatform.controller;

import ru.job4j.carsalesplatform.service.ValidateSellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCarImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarListServlet extends HttpServlet {

    private final ValidateSellingCar validateSellingCar = ValidateSellingCarImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allCars", validateSellingCar.findAllCars());
        req.getRequestDispatcher("/WEB-INF/views/CarsView.jsp").forward(req, resp);
    }
}

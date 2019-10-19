package ru.job4j.carsalesplatform.controller;

import ru.job4j.carsalesplatform.model.Seller;
import ru.job4j.carsalesplatform.model.SellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCarImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateCarServlet extends HttpServlet {
    private final ValidateSellingCar validateSellingCar = ValidateSellingCarImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("allCars", validateSellingCar.findAllCars());
        req.getRequestDispatcher("/WEB-INF/views/CarsView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int carId = Integer.parseInt(req.getParameter("carId"));
        SellingCar car = ValidateSellingCarImpl.getInstance().findCarById(carId);
        Seller seller = car.getSeller();
        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        if (seller.getLogin().equals(login)) {
            ValidateSellingCarImpl.getInstance().changeSaleStatus(carId);
            req.setAttribute("carUpdate", "Car status is changed");
            } else {
            req.setAttribute("error", "Not enough rights");
        }
        doGet(req, resp);
    }
}

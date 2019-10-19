package ru.job4j.carsalesplatform.controller;

import ru.job4j.carsalesplatform.model.Seller;
import ru.job4j.carsalesplatform.service.ValidateSellerImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddSellerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/AddSellerView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Seller seller = new Seller();
        seller.setName(req.getParameter("name"));
        seller.setLogin(req.getParameter("login"));
        seller.setPassword(req.getParameter("password"));
        seller.setPhone(Integer.parseInt(req.getParameter("phone")));
        ValidateSellerImpl.getInstance().addSeller(seller);
        req.setAttribute("userAdd", "User Successfully added");
        req.getRequestDispatcher("/WEB-INF/views/LoginView.jsp").forward(req, resp);
        }
}

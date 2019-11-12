package ru.job4j.carsalesplatform.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.carsalesplatform.model.SellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCarImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

public class ManufacturerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<SellingCar> carList = ValidateSellingCarImpl.getInstance().findAllCars();
        List<String> manufacturers = carList
                .stream()
                .map(SellingCar::getManufacturer)
                .distinct()
                .collect(Collectors.toList());
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter writer = new PrintWriter(resp.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(writer, manufacturers);
        writer.flush();
    }
}

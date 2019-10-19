package ru.job4j.carsalesplatform.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import ru.job4j.carsalesplatform.model.Seller;
import ru.job4j.carsalesplatform.model.SellingCar;
import ru.job4j.carsalesplatform.service.ValidateSeller;
import ru.job4j.carsalesplatform.service.ValidateSellerImpl;
import ru.job4j.carsalesplatform.service.ValidateSellingCar;
import ru.job4j.carsalesplatform.service.ValidateSellingCarImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AddCarServlet extends HttpServlet {

    private final static int FILE_MAX_SIZE = 1024 * 1024 * 10;
    private final ValidateSellingCar validateSellingCar = ValidateSellingCarImpl.getInstance();
    private final ValidateSeller validateSeller = ValidateSellerImpl.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/AddCarView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String> paramsMap = new HashMap<>();
        try {
            ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
            upload.setFileSizeMax(FILE_MAX_SIZE);
            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    String fieldName = item.getFieldName();
                    String fieldValue = item.getString();
                    paramsMap.put(fieldName, fieldValue);
                } else {
                    String fieldName = item.getFieldName();
                    String extension = FilenameUtils.getExtension(item.getName());
                    String fileName = String.format("photo-%s.%s", String.valueOf(System.currentTimeMillis()), extension);
                    String uploadString = System.getProperty("java.io.tmpdir") + File.separator + "photo";
                    Path uploadPath = Paths.get(uploadString);
                    Path path = Paths.get(uploadString, fileName);

                    try (InputStream fileContent = item.getInputStream()) {
                        if (!Files.exists(uploadPath)) {
                            Files.createDirectories(uploadPath);
                        }
                        Files.copy(fileContent, path);
                    }
                    paramsMap.put(fieldName, path.toString());
                }
            }
        } catch (FileUploadException e) {
            throw new ServletException("Cannot parse multipart request.", e);
        }

        SellingCar sellingCar = new SellingCar();
        sellingCar.setManufacturer(paramsMap.get("manufacturer"));
        sellingCar.setModel(paramsMap.get("model"));
        sellingCar.setMileage(Integer.parseInt(paramsMap.get("mileage")));
        sellingCar.setPrice(Integer.parseInt(paramsMap.get("price")));
        sellingCar.setProductionYear(Integer.parseInt(paramsMap.get("productionYear")));
        sellingCar.setCarBody(paramsMap.get("carBody"));
        sellingCar.setEngine(paramsMap.get("engine"));
        sellingCar.setTransmission(paramsMap.get("transmission"));
        sellingCar.setDescription(paramsMap.get("description"));
        sellingCar.setOnSale(true);

        HttpSession session = req.getSession();
        String login = (String) session.getAttribute("login");
        Seller seller = validateSeller.findSellerByLogin(login);
        sellingCar.setSeller(seller);
        this.validateSeller.updateSeller(seller);

        sellingCar.setPhoto(paramsMap.get("photoFile"));
        this.validateSellingCar.addCar(sellingCar);

        paramsMap.clear();
        resp.sendRedirect(String.format("%s/carslist", req.getContextPath()));
        }
}

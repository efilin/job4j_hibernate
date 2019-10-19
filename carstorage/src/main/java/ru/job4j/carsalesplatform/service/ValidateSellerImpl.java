package ru.job4j.carsalesplatform.service;

import ru.job4j.carsalesplatform.dao.SellerDao;
import ru.job4j.carsalesplatform.dao.SellerDaoImpl;
import ru.job4j.carsalesplatform.model.Seller;

import java.util.List;

public class ValidateSellerImpl implements ValidateSeller {

    private final SellerDao logic = SellerDaoImpl.getInstance();
    private static final ValidateSeller INSTANCE = new ValidateSellerImpl();

    public static ValidateSeller getInstance() {
        return INSTANCE;
    }

    @Override
    public int addSeller(Seller seller) {
        return this.logic.addSeller(seller);
    }

    @Override
    public void updateSeller(Seller seller) {
        this.logic.updateSeller(seller);
    }

    @Override
    public void deleteSeller(Seller seller) {
        this.logic.deleteSeller(seller);
    }

    @Override
    public List<Seller> findAllSellers() {
        return this.logic.findAllSellers();
    }

    @Override
    public Seller findSellerById(int id) {
        return this.logic.findSellerById(id);
    }

    @Override
    public Seller findSellerByLogin(String login) {
        return this.logic.findSellerByLogin(login);
    }
}

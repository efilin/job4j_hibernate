package ru.job4j.todolist;

import java.util.List;

public class ValidateService implements Validate {

    private final Store logic = DbStore.getInstance();
    private static final Validate INSTANCE = new ValidateService();

    public static Validate getInstance() {
        return INSTANCE;
    }

    @Override
    public void addItem(Item item) {
        this.logic.addItem(item);
    }

    @Override
    public List<Item> getAllItems() {
        return this.logic.getAllItems();
    }
}

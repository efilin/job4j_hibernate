package ru.job4j.todolist;

import java.util.List;

public interface Validate {
    void addItem(Item item);
    List<Item> getAllItems();
}

package ru.job4j.todolist;

import java.util.List;

public interface Store {
    void addItem(Item item);
    List<Item> getAllItems();
}

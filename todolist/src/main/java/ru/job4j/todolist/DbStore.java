package ru.job4j.todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DbStore implements Store {

    private final SessionFactory factory;

    private static final Store INSTANCE = new DbStore();

    public static Store getInstance() {
        return INSTANCE;
    }

    public DbStore() {
        factory = new Configuration()
                .configure()
                .buildSessionFactory();
    }

    @Override
    public void addItem(Item item) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            session.save(item);
            tx.commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Item> getAllItems() {
        List<Item> resultList;
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            resultList = session.createQuery("from Item").list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
        return resultList;
    }
}

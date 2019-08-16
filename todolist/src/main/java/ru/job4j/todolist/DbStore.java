package ru.job4j.todolist;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.function.Function;

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
        this.tx(session -> session.save(item));
    }

    @Override
    public List<Item> getAllItems() {
        return this.tx(session -> session.createQuery("from Item").list());
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = factory.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }
}

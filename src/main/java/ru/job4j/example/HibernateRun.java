package ru.job4j.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Timestamp;
import java.util.List;

public class HibernateRun {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory();

        createUser(factory);

        updateUser(factory);

        deleteUser(factory);

        findAllUsers(factory);

        clearTable(factory);

        factory.close();
    }

    public static void createUser(SessionFactory factory) {
        User userOne = new User();
        userOne.setName("Mike");
        userOne.setExpired(new Timestamp(System.currentTimeMillis()));
        User userTwo = new User();
        userTwo.setName("Bill");
        userTwo.setExpired(new Timestamp(System.currentTimeMillis()));
        Session session = factory.openSession();
        session.beginTransaction();
        session.save(userOne);
        session.save(userTwo);
        session.getTransaction().commit();
        System.out.println("After add two users:");
        List<User> users = session.createQuery("from User").list();
        for (User user : users) {
            System.out.println(user.toString());
        }
        session.close();
    }

    public static void updateUser(SessionFactory factory) {
        User userUpdate = new User();
        userUpdate.setName("Anton");
        userUpdate.setExpired(new Timestamp(System.currentTimeMillis()));
        Session session = factory.openSession();
        User userToUpdate = (User) session.createQuery("from User").list().get(0);
        userUpdate.setId(userToUpdate.getId());
        session.close();

        Session sessionTwo = factory.openSession();
        sessionTwo.beginTransaction();
        sessionTwo.update(userUpdate);
        sessionTwo.getTransaction().commit();
        System.out.println("After update user:");
        List<User> users = sessionTwo.createQuery("from User").list();
        for (User user : users) {
            System.out.println(user.toString());
        }
        sessionTwo.close();
    }

    public static void deleteUser(SessionFactory factory) {
        User deleteUser = new User();
        Session session = factory.openSession();
        User userToDelete = (User) session.createQuery("from User").list().get(1);
        deleteUser.setId(userToDelete.getId());
        session.close();

        Session sessionTwo = factory.openSession();
        sessionTwo.beginTransaction();
        sessionTwo.delete(deleteUser);
        sessionTwo.getTransaction().commit();
        System.out.println("After delete user:");
        List<User> users = sessionTwo.createQuery("from User").list();
        for (User user : users) {
            System.out.println(user.toString());
        }
        sessionTwo.close();
    }

    public static void findAllUsers(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();
        List<User> users = session.createQuery("from User").list();
        System.out.println("All users:");
        for (User user : users) {
            System.out.println(user.toString());
        }
        session.getTransaction().commit();
        session.close();
    }

    public static void clearTable(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

}

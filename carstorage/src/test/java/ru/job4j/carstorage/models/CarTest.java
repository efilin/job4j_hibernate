package ru.job4j.carstorage.models;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Test;


import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class CarTest {


    private final SessionFactory factory = new Configuration()
            .configure()
            .buildSessionFactory();

    private final SessionFactory annotationsFactory = new Configuration()
            .configure("annotations.cfg.xml")
            .buildSessionFactory();


    @Test
    public void whenCreate() {
        CarBody body = new CarBody("седан");
        Engine engine = new Engine("бензин 2.0");
        Transmission transmission = new Transmission("6-ступ автомат");
        Car car = new Car("audi", body, engine, transmission);
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(body);
        session.save(engine);
        session.save(transmission);
        long id = (long) session.save(car);
        tx.commit();
        session.close();
        assertThat(car.getId(), is(id));
    }

    @Test
    public void whenUpdate() {
        CarBody body = new CarBody("Хэтчбэк");
        Engine engine = new Engine("бензин 2.5");
        Transmission transmission = new Transmission("6-ступ автомат");
        Car car = new Car("BMW", body, engine, transmission);
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(body);
        session.save(engine);
        session.save(transmission);
        session.save(car);
        car.setName("VW");
        session.update(car);
        tx.commit();
        session.close();
        assertThat(car.getName(), is("VW"));
    }

    @Test
    public void whenDelete() {
        CarBody body = new CarBody("Универсал");
        Engine engine = new Engine("дизель 2.5");
        Transmission transmission = new Transmission("6-ручная");
        Car car = new Car("Ford", body, engine, transmission);
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(body);
        session.save(engine);
        session.save(transmission);
        long id = (long) session.save(car);
        session.delete(car);
        Car delCar = session.get(Car.class, id);
        tx.commit();
        session.close();
        assertNull(delCar);
    }

    @Test
    public void whenCreateWithAnnotations() {
        CarBody body = new CarBody("седан");
        Engine engine = new Engine("бензин 2.0");
        Transmission transmission = new Transmission("6-ступ автомат");
        Car car = new Car("Mercedes", body, engine, transmission);
        Session session = annotationsFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(body);
        session.save(engine);
        session.save(transmission);
        long id = (long) session.save(car);
        tx.commit();
        session.close();
        assertThat(car.getId(), is(id));
    }

    @Test
    public void whenUpdateWithAnnotations() {
        CarBody body = new CarBody("Хэтчбэк");
        Engine engine = new Engine("бензин 2.5");
        Transmission transmission = new Transmission("6-ступ автомат");
        Car car = new Car("Kia", body, engine, transmission);
        Session session = annotationsFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(body);
        session.save(engine);
        session.save(transmission);
        session.save(car);
        car.setName("Hyundai");
        session.update(car);
        tx.commit();
        session.close();
        assertThat(car.getName(), is("Hyundai"));
    }

    @Test
    public void whenDeleteWithAnnotations() {
        CarBody body = new CarBody("Универсал");
        Engine engine = new Engine("дизель 2.5");
        Transmission transmission = new Transmission("6-ручная");
        Car car = new Car("Volvo", body, engine, transmission);
        Session session = annotationsFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.save(body);
        session.save(engine);
        session.save(transmission);
        long id = (long) session.save(car);
        session.delete(car);
        Car delCar = session.get(Car.class, id);
        tx.commit();
        session.close();
        assertNull(delCar);
    }

    @After
    public void tearDown() throws Exception {
        Session session = factory.openSession();
        session.beginTransaction();
        session.createQuery("DELETE FROM Car").executeUpdate();
        session.createQuery("DELETE FROM CarBody").executeUpdate();
        session.createQuery("DELETE FROM Engine").executeUpdate();
        session.createQuery("DELETE FROM Transmission").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
package ru.job4j.carstorage.models;

import javax.persistence.*;

@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_body_id")
    private CarBody carBody;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "engine_id")
    private Engine engine;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transmission_id")
    private Transmission transmission;

    public Car() {
    }

    public Car(String name, CarBody carBody, Engine engine, Transmission transmission) {
        this.name = name;
        this.carBody = carBody;
        this.engine = engine;
        this.transmission = transmission;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CarBody getCarBody() {
        return carBody;
    }

    public void setCarBody(CarBody carBody) {
        this.carBody = carBody;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Transmission getTransmission() {
        return transmission;
    }

    public void setTransmission(Transmission transmission) {
        this.transmission = transmission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Car)) {
            return false;
        }

        Car car = (Car) o;

        if (id != car.id) {
            return false;
        }
        if (name != null ? !name.equals(car.name) : car.name != null) {
            return false;
        }
        if (carBody != null ? !carBody.equals(car.carBody) : car.carBody != null) {
            return false;
        }
        if (engine != null ? !engine.equals(car.engine) : car.engine != null) {
            return false;
        }
        return transmission != null ? transmission.equals(car.transmission) : car.transmission == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (carBody != null ? carBody.hashCode() : 0);
        result = 31 * result + (engine != null ? engine.hashCode() : 0);
        result = 31 * result + (transmission != null ? transmission.hashCode() : 0);
        return result;
    }
}

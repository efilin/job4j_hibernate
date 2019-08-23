package ru.job4j.carstorage.models;

import javax.persistence.*;

@Entity
@Table(name = "car_body")
public class CarBody {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;

    public CarBody() {
    }

    public CarBody(String name) {
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CarBody)) {
            return false;
        }

        CarBody carBody = (CarBody) o;

        if (id != carBody.id) {
            return false;
        }
        return name != null ? name.equals(carBody.name) : carBody.name == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}

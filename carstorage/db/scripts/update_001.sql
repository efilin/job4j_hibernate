CREATE TABLE car_body(
  id serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE engine(
  id serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE transmission(
  id serial PRIMARY KEY,
  name VARCHAR(100)
);

CREATE TABLE car(
  id serial PRIMARY KEY,
  name VARCHAR(2000),
  car_body_id int REFERENCES car_body(id),
  engine_id int REFERENCES engine(id),
  transmission_id int REFERENCES transmission(id)
);